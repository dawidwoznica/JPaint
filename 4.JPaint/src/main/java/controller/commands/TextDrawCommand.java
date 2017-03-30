package controller.commands;

import controller.HttpController;
import controller.JPaintViewController;
import model.Drawable;
import view.JPaintView;

/**
 * Created by Dawid on 2016-12-24.
 */
public class TextDrawCommand implements Command {

    private int figureIndex;
    private int layerIndex;
    private JPaintViewController controller;
    private JPaintView view;
    private Drawable rememberedShape;


    public TextDrawCommand(JPaintView view, JPaintViewController controller, int figureIndex, int layerIndex){
        this.figureIndex = figureIndex;
        this.layerIndex = layerIndex;
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void execute() {
        controller.getCommandStock().doneCommands.push(this);
        HttpController.postChanges(view);
    }

    @Override
    public void undo() {
        rememberedShape = controller.getPicture().getLayers().get(layerIndex).getFigures().get(figureIndex);
        controller.getPicture().getLayers().get(layerIndex).getFigures().remove(figureIndex);
        view.canvas.clearCanvas();
        controller.getPicture().redraw(view.getGc());
    }

    @Override
    public void redo() {
        controller.getPicture().getLayers().get(layerIndex).addFigure(rememberedShape);
        figureIndex = controller.getPicture().getLayers().get(layerIndex).getFigures().size()-1;
        view.canvas.clearCanvas();
        controller.getPicture().redraw(view.getGc());
    }

    public int getFigureIndex() {
        return figureIndex;
    }
}
