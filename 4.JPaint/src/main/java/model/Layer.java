package model;


import javafx.scene.canvas.GraphicsContext;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dawid on 2016-12-11.
 */
public class Layer {


    List<Drawable> figures;
    private boolean isDisabled;

    Layer(){
        this.isDisabled = false;
        this.figures= new ArrayList<Drawable>();
    }


    public void addFigure(Drawable shape) {
        figures.add(shape);
    }

    public List<Drawable> getFigures() {
        return figures;
    }

    public void enable() {
        isDisabled = false;
    }

    public void disable() {
        isDisabled = true;
    }

}
