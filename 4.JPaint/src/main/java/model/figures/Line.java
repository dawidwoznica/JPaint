package model.figures;


import controller.tools.LineTool;
import javafx.scene.canvas.GraphicsContext;


import javafx.scene.paint.Paint;
import model.Coordinates;
import model.Drawable;
import model.Strokable;


/**
 * Created by Dawid on 2016-12-11.
 */
public class Line implements Drawable, Strokable {

    private Coordinates from;
    private Coordinates to;
    private Paint strokeColor;
    private double opacity;


    public Line(LineTool tool) {
        this.from = tool.getFrom();
        this.to = tool.getTo();
        this.strokeColor = tool.getStrokeColor();
        this.opacity = tool.getOpacity();
    }

    public void draw(GraphicsContext gc) {
        setStroke(gc);
        gc.setGlobalAlpha(opacity);
        gc.strokeLine(from.getX(), from.getY(), to.getX(), to.getY());
    }

    @Override
    public void setStroke(GraphicsContext gc) {
        gc.setStroke(strokeColor);
    }
}
