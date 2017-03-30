package model.figures;

import controller.tools.GridTool;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import model.Coordinates;
import model.Drawable;
import model.Strokable;

/**
 * Created by Dawid on 2016-12-11.
 */
public class Grid implements Drawable, Strokable {
    private Coordinates first;
    private Coordinates second;
    private Paint strokeColor;
    private double opacity;

    public Grid(GridTool tool) {
        this.first = tool.getFirst();
        this.second = tool.getSecond();
        this.strokeColor = tool.getStrokeColor();
        this.opacity = tool.getOpacity();
    }

    @Override
    public void draw(GraphicsContext gc) {
        setStroke(gc);
        gc.setGlobalAlpha(opacity);
    }

    @Override
    public void setStroke(GraphicsContext gc) {
        gc.setStroke(strokeColor);
    }
}