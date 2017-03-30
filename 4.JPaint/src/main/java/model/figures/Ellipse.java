package model.figures;

import controller.tools.EllipseTool;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import model.Coordinates;
import model.Drawable;
import model.Fillable;
import model.Strokable;

import java.nio.channels.FileLock;

/**
 * Created by Dawid on 2016-12-11.
 */
public class Ellipse implements Drawable, Strokable, Fillable {
    private Coordinates first;
    private Coordinates second;
    private Paint strokeColor;
    private Paint fillColor;
    private double opacity;
    private Boolean filled;
    private Boolean stroked;


    public Ellipse(EllipseTool tool) {
        this.first = tool.getFirst();
        this.second = tool.getSecond();
        this.strokeColor = tool.getStrokeColor();
        this.fillColor = tool.getFillColor();
        this.filled = tool.getFilled();
        this.stroked = tool.getStroked();
        this.opacity = tool.getOpacity();

        //this.width = second.getX() - first.getX();
        //this.height = second.getY() - first.getY();
        /*
        if (width < 0) {
            width = -width;
            first.setX(second.getX());
        }

        if (height < 0) {
            height = -height;
            first.setY(second.getY());
        }
        */
        if(second.getX()>first.getX()&&second.getY()>first.getY()) {
            this.first = new Coordinates(first.getX(), first.getY());
            this.second = new Coordinates(second.getX() - first.getX(), second.getY() - first.getY());
        }
        else if(second.getX()>first.getX()&&second.getY()<first.getY()){
            this.first = new Coordinates(first.getX(), second.getY());
            this.second = new Coordinates(second.getX()-first.getX(), first.getY()-second.getY());
        }
        else if(second.getY()<first.getY()&&second.getX()<first.getX()){
            this.first = new Coordinates(second.getX(), second.getY());
            this.second = new Coordinates(first.getX()-second.getX(), first.getY()-second.getY());
        }
        else if(second.getX()<first.getX()&&second.getY()>first.getY()){
            this.first = new Coordinates(second.getX(), first.getY());
            this.second = new Coordinates(first.getX()-second.getX(), second.getY()-first.getY());
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        setFill(gc);
        setStroke(gc);
        gc.setGlobalAlpha(opacity);
        if(filled) {
            gc.fillOval(first.getX(), first.getY(), second.getX(), second.getY());
        }
        if(stroked) {
            gc.strokeOval(first.getX(), first.getY(), second.getX(), second.getY());
        }
        //gc.strokeOval(first.getX(), first.getY(), width, height);
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
