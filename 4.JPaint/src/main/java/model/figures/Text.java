package model.figures;

import controller.tools.TextTool;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.Coordinates;
import model.Drawable;
import model.Fillable;
import model.Strokable;
import org.apache.logging.log4j.core.layout.HtmlLayout;

/**
 * Created by Dawid on 2016-12-11.
 */
public class Text implements Drawable, Strokable, Fillable {
    private Coordinates from;
    private String input;
    private Paint strokeColor;
    private Paint fillColor;
    private double opacity;
    private Font font;
    private Boolean filled;
    private Boolean stroked;

    public Text(TextTool tool) {
        this.from = tool.getFirst();
        this.strokeColor = tool.getStrokeColor();
        this.fillColor = tool.getFillColor();
        this.input = tool.getInput();
        this.font = tool.getFont();
        this.opacity = tool.getOpacity();
        this.filled = tool.getFilled();
        this.stroked = tool.getStroked();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFont(font);
        gc.setGlobalAlpha(opacity);
        setStroke(gc);
        setFill(gc);

        if(filled) {
            gc.fillText(input, from.getX(), from.getY());
        }
        if(stroked) {
            gc.strokeText(input, from.getX(), from.getY());
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