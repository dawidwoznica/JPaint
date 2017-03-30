package view;


import com.google.gson.Gson;
import com.oracle.javafx.scenebuilder.kit.util.control.paintpicker.PaintPicker;
import com.oracle.javafx.scenebuilder.kit.util.control.paintpicker.PaintPickerController;
import controller.*;
import controller.tools.*;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


/**
 * Created by s412162 on 02.12.2016.
 */

public class JPaintView extends Application {
    private GraphicsContext gc;
    public ResizableCanvas canvas;
    private Tool tool;
    private JPaintViewController controller;
    private BorderPane root;
    public final ColorPicker strokeColorPicker = new ColorPicker();
    private CheckBox noStrokeCheckBox;
    private CheckBox noFillCheckBox;
    private Stage paintPickerStage = new Stage();
    private final BooleanProperty zPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty ctrlPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty yPressed = new SimpleBooleanProperty(false);
    private final BooleanBinding yAndCtrlPressed = yPressed.and(ctrlPressed);
    private final BooleanBinding zAndCtrlPressed = zPressed.and(ctrlPressed);




    //public final ColorPicker fillColorPicker = new ColorPicker();


    public class ResizableCanvas extends Canvas {

        public ResizableCanvas() {
            gc = getGraphicsContext2D();
            // Redraw canvas when size changes.
            widthProperty().addListener(evt -> clearCanvas());
            heightProperty().addListener(evt -> clearCanvas());
        }

        public void clearCanvas() {
            double width = getWidth();
            double height = getHeight();

            gc.clearRect(0, 0, width, height);
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, width, height);
            controller.getPicture().redraw(gc);
        }

        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefWidth(double width) {
            return getWidth();
        }

        @Override
        public double prefHeight(double height) {
            return getHeight();
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        controller = new JPaintViewController(this);
        canvas = new ResizableCanvas();
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            try {
                tool.handleMousePressed(event);
            }
            catch(NullPointerException e){
                System.out.println("Can't handle MOUSE_PRESSED because there's no tool selected.");
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            try{
                tool.handleMouseReleased(event);
            }
            catch(NullPointerException e){
                System.out.println("Can't handle MOUSE_RELEASED because there's no tool selected.");
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try{
                tool.handleMouseClicked(event);
            }
            catch(NullPointerException e){
                System.out.println("Can't handle MOUSE_CLICKED because there's no tool selected.");
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            try{
                tool.handleMouseDragged(event);
            }
            catch(NullPointerException e){
                System.out.println("Can't handle MOUSE_DRAGGED because there's no tool selected.");
            }
        });

        root = new BorderPane();
        VBox topContainer = new VBox();
        MenuBar mainMenu = new MenuBar();
        ToolBar settingBar = new ToolBar();
        ToolBar toolBar = new ToolBar();

        topContainer.getChildren().add(mainMenu);
        topContainer.getChildren().add(settingBar);
        topContainer.getChildren().add(toolBar);
        topContainer.getChildren().add(canvas);
        root.setTop(topContainer);

        //file menu
        Menu file = new Menu("File");

        MenuItem openFile = new MenuItem("Open File");
        openFile.setOnAction(event -> controller.openNewFile());

        MenuItem saveFile = new MenuItem("Save");
        saveFile.setOnAction(event -> controller.saveFile());

        MenuItem exitApp = new MenuItem("Quit");
        exitApp.setOnAction(event -> controller.closeApp());

        file.getItems().addAll(openFile, saveFile, exitApp);

        //view menu
        Menu edit = new Menu("View");

        //help menu
        Menu help = new Menu("Help");

        MenuItem visitWebsite = new MenuItem("Visit Website");
        help.getItems().add(visitWebsite);

        mainMenu.getMenus().addAll(file, edit, help);


        //buttons
        Button drawCurveButton = new Button();
        drawCurveButton.setOnAction(event -> tool = new CurveTool(this));

        Button drawLineButton = new Button();
        drawLineButton.setOnAction(event -> tool = new LineTool(this));

        Button drawTriangleButton = new Button();
        drawTriangleButton.setOnAction(event -> tool = new TriangleTool(this));

        Button drawRectangleButton = new Button();
        drawRectangleButton.setOnAction(event -> tool = new RectangleTool(this));

        Button drawEllipseButton = new Button();
        drawEllipseButton.setOnAction(event -> tool = new EllipseTool(this));

        Button writeTextButton = new Button();
        writeTextButton.setOnAction(event -> tool = new TextTool(this));

        drawCurveButton.setGraphic(new ImageView("/icons/curve.png"));
        drawLineButton.setGraphic(new ImageView("/icons/line.png"));
        drawTriangleButton.setGraphic(new ImageView("/icons/triangle.png"));
        drawRectangleButton.setGraphic(new ImageView("/icons/rectangle.png"));
        drawEllipseButton.setGraphic(new ImageView("/icons/ellipse.png"));
        writeTextButton.setGraphic(new ImageView("/icons/text.png"));

        toolBar.getItems().addAll(drawCurveButton, drawLineButton, drawTriangleButton, drawRectangleButton, drawEllipseButton, writeTextButton);

        //color
        Label strokeColorLabel = new Label();
        strokeColorLabel.setText("Stroke: ");
        strokeColorPicker.setOnAction(event -> controller.strokeColorSelect());
        strokeColorPicker.setValue(Color.BLACK);
        strokeColorPicker.setStyle("-fx-color-label-visible: false ;");

        noStrokeCheckBox = new CheckBox();
        noStrokeCheckBox.setText("No stroke");
        noStrokeCheckBox.setOnAction(event -> {
            controller.toStrokeOrNotToStroke(noStrokeCheckBox);
            if(noStrokeCheckBox.isSelected()){
                noFillCheckBox.setSelected(false);
                controller.toFillOrNotToFill(noFillCheckBox);
            }
        });


        /*
        Label fillColorLabel = new Label();
        fillColorLabel.setText(" Fill: ");
        fillColorPicker.setOnAction(event -> controller.fillColorSelect());
        fillColorPicker.setValue(Color.WHITE);
        fillColorPicker.setStyle("-fx-color-label-visible: false ;");
        */


        //zapozyczone ze SceneBuilder-a, jar do załączenia w folderze, nie do konca wiedzialem jak dodac go na stale
        VBox secondRoot = new VBox();
        secondRoot.setAlignment(Pos.CENTER);
        secondRoot.setPadding(new Insets(10));

        PaintPickerController paintPickerController;
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PaintPicker.class.getResource("PaintPicker.fxml"));

        try {
            final VBox picker = loader.load();
            paintPickerController = loader.getController();
            paintPickerController.paintProperty().addListener((obs, ov, nv) -> controller.setFillColor(nv));
            secondRoot.getChildren().add(picker);
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Scene paintPickerScene = new Scene(secondRoot, 320, 600);

        paintPickerStage.setTitle("JPaint - Fill");
        paintPickerStage.getIcons().add(new Image("/icons/icon.png"));
        paintPickerStage.setScene(paintPickerScene);


        Button fillButton = new Button();
        fillButton.setText("Fill");
        fillButton.setOnAction(event -> controller.showGradientPicker(paintPickerStage));

        noFillCheckBox = new CheckBox();
        noFillCheckBox.setText("No fill");
        noFillCheckBox.setOnAction(event -> {
            controller.toFillOrNotToFill(noFillCheckBox);
            if(noFillCheckBox.isSelected()){
                noStrokeCheckBox.setSelected(false);
                controller.toStrokeOrNotToStroke(noStrokeCheckBox);
            }
        });


        Label opacityLabel = new Label();
        opacityLabel.setText("Opacity: ");

        Slider opacitySlider = new Slider(0, 1, 1);
        opacitySlider.setShowTickLabels(true);
        opacitySlider.setShowTickMarks(true);
        opacitySlider.setBlockIncrement(0.01f);
        opacitySlider.setMajorTickUnit(0.1f);
        opacitySlider.setMinorTickCount(10);
        opacitySlider.setOnMouseReleased(event -> controller.changeOpacity(opacitySlider));


        //layers
        ComboBox<String> layerComboBox = new ComboBox<>();
        layerComboBox.getItems().add("Layer 1");
        layerComboBox.getSelectionModel().selectFirst();
        layerComboBox.setOnAction(event -> controller.changeCurrentLayer(layerComboBox));


        Button newLayerButton = new Button();
        newLayerButton.setText("New layer");
        newLayerButton.setOnAction(event -> controller.newLayer(layerComboBox));

        Button moveUpButton = new Button();
        moveUpButton.setText("Move up");
        moveUpButton.setOnAction(event -> controller.moveLayerUp(layerComboBox));

        /*
        Button moveDownButton = new Button();
        moveDownButton.setText("Move down");
        moveDownButton.setOnAction(event -> controller.moveLayerDown(layerComboBox));
        */

        Label freeSpace = new Label();
        freeSpace.setText("      ");


        Button undoButton = new Button();
        undoButton.setOnAction(event -> controller.undoSelected());
        undoButton.setGraphic(new ImageView("/icons/undo.jpg"));

        Button redoButton = new Button();
        redoButton.setOnAction(event -> controller.redoSelected());
        redoButton.setGraphic(new ImageView("/icons/redo.jpg"));


        settingBar.getItems().addAll(undoButton, redoButton, strokeColorLabel, strokeColorPicker, noStrokeCheckBox, fillButton, noFillCheckBox, opacityLabel, opacitySlider, freeSpace, layerComboBox, newLayerButton, moveUpButton);



        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());



        Scene mainScene = new Scene(root, 800, 600);







        mainScene.setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.Z) {
                zPressed.set(true);
            } else if (ke.getCode() == KeyCode.CONTROL) {
                ctrlPressed.set(true);
            }else if(ke.getCode() == KeyCode.Y){
                yPressed.set(true);
            }
        });

        mainScene.setOnKeyReleased(ke -> {
            if (ke.getCode() == KeyCode.Z) {
                zPressed.set(false);
            } else if (ke.getCode() == KeyCode.CONTROL) {
                ctrlPressed.set(false);
            } else if (ke.getCode() == KeyCode.Y) {
                yPressed.set(false);
            }
        });

        ///ctrl+Z
        zAndCtrlPressed.addListener((observable, oldValue, newValue) -> {
            if(newValue){
                controller.getCommandStock().undo();
                canvas.clearCanvas();
                controller.getPicture().redraw(gc);
            }
        });


        //ctrl+Y
        yAndCtrlPressed.addListener((observable, oldValue, newValue) -> {
            if(newValue){
                controller.getCommandStock().redo();
                canvas.clearCanvas();
                controller.getPicture().redraw(gc);
            }
        });




        //stage
        stage.setScene(mainScene);
        stage.setTitle("JPaint");
        stage.getIcons().add(new Image("/icons/icon.png"));
        stage.setOnCloseRequest(event -> {
            controller.getTextStage().close();
            paintPickerStage.close();
        });
        stage.show();
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public JPaintViewController getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public BorderPane getRoot() {
        return root;
    }

    public GraphicsContext getGc(){
        return gc;
    }
}
