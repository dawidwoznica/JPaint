package controller.tools;

import controller.commands.Command;
import javafx.scene.input.MouseEvent;
import model.Coordinates;
import model.Drawable;

import view.JPaintView;

//import static view.JPaintView.canvas;

/**
 * Created by roxwo on 10.12.2016.
 */

public interface Tool {


    void handleMousePressed(MouseEvent event);

    void handleMouseReleased(MouseEvent event);

    void handleMouseClicked(MouseEvent event);

    void handleMouseDragged(MouseEvent event);



/*
    //public abstract void whichShape();

    public void handleMousePressed(MouseEvent event) {
        first = new Coordinates(event.getX(), event.getY());
    }


    public void handleMouseReleased(MouseEvent event) {
        second = new Coordinates(event.getX(), event.getY());
        whichShape();
        shape.draw(view.gc);
        view.picture.getCurrentLayer().addFigure(shape);
    }

    public void handleMouseClicked(MouseEvent event) {

    }

    public void handleMouseDragged(MouseEvent event) {
        view.canvas.clearCanvas();
        second = new Coordinates(event.getX(), event.getY());
        whichShape();
        shape.draw(view.gc);
        view.picture.getCurrentLayer().redraw(view.gc);

    }

    public Coordinates getFirst() {
        return first;
    }

    public Coordinates getSecond() {
        return second;
    }

    public void setFirst(Coordinates first) {
        this.first = first;
    }

    public void setSecond(Coordinates second) {
        this.second = second;
    }

*/

}
