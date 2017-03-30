package controller.tools;

import controller.JPaintViewController;
import controller.commands.CurveDrawCommand;
import controller.commands.LineDrawCommand;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import model.Coordinates;
import model.figures.Curve;
import view.JPaintView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dawid on 2016-12-11.
 */
public class CurveTool implements Tool {

    private List<Coordinates> points;
    private Coordinates point;
    private JPaintView view;
    private JPaintViewController controller;
    private Paint strokeColor;
    private double opacity;

    public CurveTool(JPaintView jPaintView){
        this.view = jPaintView;
        this.controller = jPaintView.getController();
    }

    @Override
    public void handleMousePressed(MouseEvent event) {
        strokeColor = controller.getStrokeColor();
        opacity = controller.getOpacityValue();
        points = new ArrayList<Coordinates>();
        point = new Coordinates(event.getX(), event.getY());
        points.add(point);
    }

    @Override
    public void handleMouseReleased(MouseEvent event) {
        point = new Coordinates(event.getX(), event.getY());
        points.add(point);
        Curve curve = new Curve(this);
        //curve.draw(view.gc);
        controller.getPicture().getCurrentLayer().addFigure(curve);
        controller.getPicture().redraw(view.getGc());
        int figureIndex = controller.getPicture().getCurrentLayer().getFigures().size() - 1;
        int layerIndex = controller.getPicture().getCurrentLayerIndex();
        controller.setCommand(new CurveDrawCommand(view, controller, figureIndex, layerIndex));
        controller.getCommand().execute();
    }

    @Override
    public void handleMouseClicked(MouseEvent event) {

    }

    @Override
    public void handleMouseDragged(MouseEvent event) {
        view.canvas.clearCanvas();
        point = new Coordinates(event.getX(), event.getY());
        points.add(point);
        Curve curve = new Curve(this);
        controller.getPicture().redraw(view.getGc());
        curve.draw(view.getGc());

    }

    public Paint getStrokeColor() {
        return strokeColor;
    }

    public List<Coordinates> getPoints() {
        return points;
    }

    public double getOpacity() {
        return opacity;
    }
}
