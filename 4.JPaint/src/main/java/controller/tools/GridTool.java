package controller.tools;

import controller.JPaintViewController;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import model.Coordinates;
import view.JPaintView;

/**
 * Created by Dawid on 2016-12-25.
 */
public class GridTool implements Tool {
    private Paint strokeColor;
    private double opacity;
    private JPaintViewController controller;
    private Coordinates first;
    private Coordinates second;
    private JPaintView view;

    public GridTool(JPaintView jPaintView){
        this.view = jPaintView;
        this.controller = jPaintView.getController();
    }

    @Override
    public void handleMousePressed(MouseEvent event) {
        strokeColor = controller.getStrokeColor();
        opacity = controller.getOpacityValue();

    }

    @Override
    public void handleMouseReleased(MouseEvent event) {

    }

    @Override
    public void handleMouseClicked(MouseEvent event) {

    }

    @Override
    public void handleMouseDragged(MouseEvent event) {

    }


    public Paint getStrokeColor() {
        return strokeColor;
    }

    public Coordinates getFirst() {
        return first;
    }

    public Coordinates getSecond() {
        return second;
    }

    public double getOpacity() {
        return opacity;
    }
}
