package controller.tools;

import controller.JPaintViewController;
import controller.commands.TextDrawCommand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.annotations.Ignore;
import model.Coordinates;
import model.figures.Text;
import view.JPaintView;

/**
 * Created by Dawid on 2016-12-13.
 */
public class TextTool implements Tool {

    private JPaintViewController controller;
    private Coordinates from;
    private JPaintView view;
    private Paint strokeColor;
    private Paint fillColor;
    private double opacity;
    private String input;
    private Font font;
    private Stage textStage;
    private Boolean filled;
    private Boolean stroked;

    public TextTool(JPaintView jPaintView){
        this.view = jPaintView;
        this.controller = jPaintView.getController();
    }

    @Override
    public void handleMouseClicked(MouseEvent event){
        VBox textCreator = new VBox();

        TextField textInput = new TextField();
        textInput.setText("text here");

        Scene textScene = new Scene(textCreator, 300, 65);

        textStage = controller.getTextStage();
        textStage.setScene(textScene);
        textStage.setTitle("JPaint - Text creator");
        textStage.getIcons().add(new Image("/icons/icon.png"));
        textStage.setResizable(false);


        TextField textSize = new TextField();
        textSize.setText("font size here");
        textSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textSize.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        ChoiceBox fontStyle = new ChoiceBox();
        fontStyle.getItems().addAll("Arial", "Comic Sans MS", "Courier New", "Georgia", "Times New Roman");


        fontStyle.getSelectionModel().selectFirst();


        //textStage.initStyle(StageStyle.UNDECORATED);

        textCreator.getChildren().add(textInput);
        textCreator.getChildren().add(textSize);
        textCreator.getChildren().add(fontStyle);
        //textCreator.getChildren().add(fontStyle);

        TextTool tool = this;
        textScene.setOnKeyPressed( new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    input = textInput.getText();
                    try{
                        /*
                        String path = "file:resources/fonts/BeyondTheMountains.ttf";
                        if(fontStyle.getSelectionModel().getSelectedIndex() == 0){
                            path = "file:resources/fonts/BeyondTheMountains.ttf";
                        }
                        else if(fontStyle.getSelectionModel().getSelectedIndex() == 1){
                            path = "file:resources/fonts/GentlemanOnTheRainbow.ttf";
                        }
                        else if(fontStyle.getSelectionModel().getSelectedIndex() == 2){
                            path = "file:resources/fonts/RaiderCrusader.ttf";
                        }
                        else if(fontStyle.getSelectionModel().getSelectedIndex() == 3){
                            path = "file:resources/fonts/RoadRage.otf";
                        }
                        */
                        //font = Font.loadFont(path, Integer.valueOf(textSize.getText()));
                        font = new Font(fontStyle.getSelectionModel().getSelectedItem().toString(), Integer.valueOf(textSize.getText()));
                    }
                    catch(NumberFormatException e){
                        System.out.println("Font size field empty. Previous settings used.");
                    }

                    textStage.close();
                    //System.out.println(fontStyle.getSelectionModel().getSelectedItem());


                    filled = !controller.getNoFillSelected();
                    stroked = !controller.getNoStrokeSelected();
                    strokeColor = controller.getStrokeColor();
                    fillColor = controller.getFillColor();
                    opacity = controller.getOpacityValue();
                    from = new Coordinates(event.getX(), event.getY());
                    Text text = new Text(tool);
                    //text.draw(view.gc);
                    controller.getPicture().getCurrentLayer().addFigure(text);
                    controller.getPicture().redraw(view.getGc());
                    int figureIndex = controller.getPicture().getCurrentLayer().getFigures().size() - 1;
                    int layerIndex = controller.getPicture().getCurrentLayerIndex();
                    controller.setCommand(new TextDrawCommand(view, controller, figureIndex, layerIndex));
                    controller.getCommand().execute();
                }
            }
        });

        textStage.show();
    }


    @Ignore
    public void handleMouseReleased(MouseEvent event){
    }

    @Ignore
    public void handleMousePressed(MouseEvent event){
    }

    @Ignore
    public void handleMouseDragged(MouseEvent event){
    }

    public Coordinates getFirst() {
        return from;
    }

    public Paint getStrokeColor() {
        return strokeColor;
    }

    public String getInput() {
        return input;
    }

    public Font getFont(){
        return font;
    }

    public double getOpacity(){
        return opacity;
    }

    public Paint getFillColor(){
        return fillColor;
    }

    public Boolean getFilled() {
        return filled;
    }

    public Boolean getStroked() {
        return stroked;
    }

}
