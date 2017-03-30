package model.figures;

import controller.tools.CurveTool;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.Coordinates;
import model.Drawable;
import model.Strokable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 2016-12-11.
 */
public class Curve implements Drawable, Strokable {

    private List<Coordinates> points;
    private Paint strokeColor;
    private double opacity;

    public Curve(CurveTool tool) {
        this.points = new ArrayList<Coordinates>();
        this.points = tool.getPoints();
        this.strokeColor = tool.getStrokeColor();
        this.opacity = tool.getOpacity();
    }

    @Override
    public void draw(GraphicsContext gc) {
        setStroke(gc);
        gc.setGlobalAlpha(opacity);
        gc.beginPath();
        gc.moveTo(points.get(0).getX(), points.get(0).getY());
        gc.stroke();
        for (int j = 0; j < points.size(); j++) {
            gc.lineTo(points.get(j).getX(), points.get(j).getY());
            gc.stroke();
        }
        gc.closePath();
    }

    @Override
    public void setStroke(GraphicsContext gc) {
        gc.setStroke(strokeColor);
    }
}