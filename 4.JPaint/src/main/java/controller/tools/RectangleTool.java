package controller.tools;

import controller.JPaintViewController;
import controller.commands.RectangleDrawCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import model.Coordinates;
import model.figures.Rectangle;
import view.JPaintView;


/**
 * Created by Dawid on 2016-12-11.
 */
public class RectangleTool implements Tool {

    private Paint strokeColor;
    private Paint fillColor;
    private double opacity;
    private JPaintViewController controller;
    private Coordinates first;
    private Coordinates second;
    private JPaintView view;
    private Boolean filled;
    private Boolean stroked;
    final BooleanProperty isShiftPressed = new SimpleBooleanProperty(false);




    public RectangleTool(JPaintView jPaintView){
        this.view = jPaintView;
        this.controller = jPaintView.getController();

        view.getRoot().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.SHIFT) {
                    isShiftPressed.set(true);
                }
            }
        });

        view.getRoot().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.SHIFT) {
                    isShiftPressed.set(false);
                }
            }
        });
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
        Rectangle rectangle = new Rectangle(this);
        //rectangle.draw(view.gc);
        controller.getPicture().getCurrentLayer().addFigure(rectangle);
        controller.getPicture().redraw(view.getGc());
        int figureIndex = controller.getPicture().getCurrentLayer().getFigures().size() - 1;
        int layerIndex = controller.getPicture().getCurrentLayerIndex();
        controller.setCommand(new RectangleDrawCommand(view, controller, figureIndex, layerIndex));
        controller.getCommand().execute();
    }

    @Override
    public void handleMouseClicked(MouseEvent event) {

    }

    @Override
    public void handleMouseDragged(MouseEvent event) {
        view.canvas.clearCanvas();
        second = new Coordinates(event.getX(), event.getY());
        Rectangle rectangle = new Rectangle(this);
        controller.getPicture().redraw(view.getGc());
        rectangle.draw(view.getGc());

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
