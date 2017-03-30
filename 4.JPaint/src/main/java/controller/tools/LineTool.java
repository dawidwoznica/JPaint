package controller.tools;

import controller.JPaintViewController;
import controller.commands.Command;
import controller.commands.LineDrawCommand;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import model.Coordinates;
import model.figures.Line;
import view.JPaintView;

/**
 * Created by roxwo on 10.12.2016.
 */

public class LineTool implements Tool {

    private Paint strokeColor;
    private double opacity;
    private JPaintViewController controller;
    private Coordinates from;
    private Coordinates to;
    private JPaintView view;

    public LineTool(JPaintView jPaintView){
        this.controller = jPaintView.getController();
        this.view = jPaintView;
    }

    @Override
    public void handleMousePressed(MouseEvent event) {
        strokeColor = controller.getStrokeColor();
        opacity = controller.getOpacityValue();
        from = new Coordinates(event.getX(), event.getY());
    }

    @Override
    public void handleMouseReleased(MouseEvent event) {
        to = new Coordinates(event.getX(), event.getY());
        Line line = new Line(this);
        //line.draw(view.gc);
        controller.getPicture().getCurrentLayer().addFigure(line);
        controller.getPicture().redraw(view.getGc());
        int figureIndex = controller.getPicture().getCurrentLayer().getFigures().size() - 1;
        int layerIndex = controller.getPicture().getCurrentLayerIndex();
        controller.setCommand(new LineDrawCommand(view, controller, figureIndex, layerIndex));
        controller.getCommand().execute();
    }

    @Override
    public void handleMouseClicked(MouseEvent event) {

    }

    @Override
    public void handleMouseDragged(MouseEvent event) {
        view.canvas.clearCanvas();
        to = new Coordinates(event.getX(), event.getY());
        Line line = new Line(this);
        controller.getPicture().redraw(view.getGc());
        line.draw(view.getGc());
    }


    public Coordinates getFrom() {
        return from;
    }

    public Coordinates getTo() {
        return to;
    }

    public LineTool returnTool(){
      return this;
    }

    public Paint getStrokeColor() {
        return strokeColor;
    }


    public double getOpacity() {
        return opacity;
    }
}


