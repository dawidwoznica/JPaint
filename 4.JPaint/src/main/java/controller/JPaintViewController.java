package controller;


import java.io.File;

import com.google.gson.Gson;
import controller.commands.Command;
import controller.commands.CommandStock;
import javafx.application.Platform;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Picture;
import sun.net.www.http.HttpClient;
import view.JPaintView;

/**
 * Created by Dawid on 2016-12-19.
 */
public class JPaintViewController {
    JPaintView view;

    private Paint strokeColor = Color.BLACK;
    private Paint fillColor = Color.WHITE;
    private double opacityValue = 1.0f;
    private Picture picture = new Picture();
    private Command command;
    private Boolean isNoFillSelected = false;
    private Boolean isNoStrokeSelected = false;
    private CommandStock commandStock = new CommandStock();
    private Stage textStage= new Stage();
    //public final Gson gson = new Gson();
    //public String jsonPicture;


    public JPaintViewController(JPaintView jPaintView){
        this.view = jPaintView;
    }



    private FileChooser FileChooserConfig() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter(".jpg", "*.jpg"),
                new FileChooser.ExtensionFilter(".png", "*.png"),
                new FileChooser.ExtensionFilter(".bmp", "*.bmp")
        );
        return fileChooser;
    }
    /*
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    public void openNewFile() {
        File file = FileChooserConfig().showOpenDialog(new Stage());
        if (file != null) {
           //opening here
        }
    }
    public void saveFile(){
        File file = FileChooserConfig().showSaveDialog(new Stage());
        if (file != null) {

            //saveing here

        }
    }

    public Picture getPicture() {
        return picture;
    }

    public Paint getFillColor() {
        return fillColor;
    }

    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
    }

    public Paint getStrokeColor() {
        return strokeColor;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void closeApp(){
        Platform.exit();
    }

    public void newLayer(ComboBox layerComboBox) {
        picture.addLayer();
        layerComboBox.getItems().add("Layer " + String.valueOf(picture.getNumberOfLayers()));
    }

    public void changeCurrentLayer(ComboBox layerComboBox) {
        picture.setCurrentLayer(picture.getLayers().get(layerComboBox.getSelectionModel().getSelectedIndex()));
        //System.out.println(layerComboBox.getSelectionModel().getSelectedIndex());
    }

    public void moveLayerUp(ComboBox layerComboBox) {
        try{
            int index = layerComboBox.getSelectionModel().getSelectedIndex();
            picture.moveUp(picture.getLayers().get(index));
            Object cache = layerComboBox.getItems().get(index-1);
            Object movedLayer = layerComboBox.getItems().get(index);
            layerComboBox.getItems().set(index-1, movedLayer);
            layerComboBox.getItems().set(index, cache);
            //layerComboBox.getSelectionModel().select(index-1);
            //layerComboBox.getSelectionModel().selectPrevious();
            changeCurrentLayer(layerComboBox);
            view.canvas.clearCanvas();
            picture.redraw(view.getGc());
        }
        catch(NullPointerException e) {
            System.out.println("Layer can't be moved upper.");
        }

    }

    /*
    public void moveLayerDown(ComboBox layerComboBox) {
        //try{
            int index = layerComboBox.getSelectionModel().getSelectedIndex();
            picture.moveDown(picture.getLayers().get(index));
            Object cache = layerComboBox.getItems().get(index+1);
            Object movedLayer = layerComboBox.getItems().get(index);
            layerComboBox.getItems().set(index+1, movedLayer);
            layerComboBox.getItems().set(index, cache);
            layerComboBox.getSelectionModel().selectNext();
            changeCurrentLayer(layerComboBox);
            picture.redraw(view.gc);
        //}
        //catch(IndexOutOfBoundsException e){
         //   System.out.println("Layer can't be moved lower.");
        //}
    }
    */

    public void strokeColorSelect(){
        strokeColor = view.strokeColorPicker.getValue();
    }

    /*
    public void fillColorSelect() {
        fillColor = view.fillColorPicker.getValue();
    }
    */

    public void showGradientPicker(Stage stage) {
        stage.show();
    }

    public void toFillOrNotToFill(CheckBox noFillCheckBox) {
        if(noFillCheckBox.isSelected()){
            isNoFillSelected = true;
        }
        else{
            isNoFillSelected = false;
        }
    }

    public void toStrokeOrNotToStroke(CheckBox noStrokeCheckBox) {
        if(noStrokeCheckBox.isSelected()){
            isNoStrokeSelected = true;
        }
        else{
            isNoStrokeSelected = false;
        }
    }

    public Boolean getNoFillSelected() {
        return isNoFillSelected;
    }

    public Boolean getNoStrokeSelected() {
        return isNoStrokeSelected;
    }

    public CommandStock getCommandStock() {
        return commandStock;
    }

    public Stage getTextStage() {
        return textStage;
    }


    public void undoSelected() {
        commandStock.undo();
    }

    public void redoSelected() {
        commandStock.redo();
    }

    public void changeOpacity(Slider opacitySlider) {
        opacityValue = opacitySlider.getValue();
    }

    public double getOpacityValue() {
        return opacityValue;
    }
}