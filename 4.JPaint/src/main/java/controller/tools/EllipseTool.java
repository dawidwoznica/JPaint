package controller.tools;

import controller.JPaintViewController;
import controller.commands.EllipseDrawCommand;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import model.Coordinates;
import model.figures.Ellipse;
import view.JPaintView;

/**
 * Created by Dawid on 2016-12-11.
 */
public class EllipseTool implements Tool {

    private Paint strokeColor;
    private Paint fillColor;
    private double opacity;
    private JPaintViewController controller;
    private Coordinates first;
    private Coordinates second;
    private JPaintView view;
    private Boolean filled;
    private Boolean stroked;


    public EllipseTool(JPaintView jPaintView){
        this.view = jPaintView;
        this.controller = jPaintView.getController();
    }

    @Override
    public void handleMousePressed(MouseEvent event) {
        filled = !controller.getNoFillSelected();
        stroked = !controller.getNoStrokeSelected();
        strokeColor = controller.getStrokeColor();
        fillColor = controller.getFillColor();
        opacity = controller.getOpacityValue();
        first = new Coordinates(event.getX(), event.getY());
    }

    @Override
    public void handleMouseReleased(MouseEvent event) {
        second = new Coordinates(event.getX(), event.getY());
        Ellipse ellipse = new Ellipse(this);
        //ellipse.draw(view.gc);
        controller.getPicture().getCurrentLayer().addFigure(ellipse);
        controller.getPicture().redraw(view.getGc());
        int figureIndex = controller.getPicture().getCurrentLayer().getFigures().size() - 1;
        int layerIndex = controller.getPicture().getCurrentLayerIndex();
        controller.setCommand(new EllipseDrawCommand(view, controller, figureIndex, layerIndex));
        controller.getCommand().execute();
    }

    @Override
    public void handleMouseClicked(MouseEvent event) {

    }

    @Override
    public void handleMouseDragged(MouseEvent event) {
        view.canvas.clearCanvas();
        second = new Coordinates(event.getX(), event.getY());
        Ellipse ellipse = new Ellipse(this);
        controller.getPicture().redraw(view.getGc());
        ellipse.draw(view.getGc());

    }


    public Coordinates getFirst() {
        return first;
    }

    public Coordinates getSecond() {
        return second;
    }

    public Paint getStrokeColor() {
        return strokeColor;
    }

    public Paint getFillColor() {
        return fillColor;
    }

    public Boolean getFilled() {
        return filled;
    }
    public Boolean getStroked() {
        return stroked;
    }


    public double getOpacity() {
        return opacity;
    }
}