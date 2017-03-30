package model.figures;

import controller.tools.RectangleTool;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import model.Coordinates;
import model.Drawable;
import model.Fillable;
import model.Strokable;

/**
 * Created by Dawid on 2016-12-11.
 */
public class Rectangle implements Drawable, Strokable, Fillable {
    private Coordinates first;
    private Coordinates second;
    private Paint strokeColor;
    private Paint fillColor;
    private double opacity;
    private Boolean filled;
    private Boolean stroked;

    public Rectangle(RectangleTool tool) {
        this.first = tool.getFirst();
        this.second = tool.getSecond();
        this.strokeColor = tool.getStrokeColor();
        this.fillColor = tool.getFillColor();
        this.filled = tool.getFilled();
        this.stroked = tool.getStroked();
        this.opacity = tool.getOpacity();
    }

    public void draw(GraphicsContext gc) {
        setFill(gc);
        setStroke(gc);
        gc.setGlobalAlpha(opacity);
        if(filled) {
            gc.fillPolygon(new double[]{first.getX(), first.getX(), second.getX(), second.getX()},
                    new double[]{first.getY(), second.getY(), second.getY(), first.getY()}, 4);
        }
        if(stroked) {
            gc.strokePolygon(new double[]{first.getX(), first.getX(), second.getX(), second.getX()},
                    new double[]{first.getY(), second.getY(), second.getY(), first.getY()}, 4);
        }
    }

    @Override
    public void setStroke(GraphicsContext gc) {
        gc.setStroke(strokeColor);
    }

    @Override
    public void setFill(GraphicsContext gc) {
        gc.setFill(fillColor);
    }
}