package controller.commands;

import controller.JPaintViewController;
import controller.tools.Tool;
import view.JPaintView;

/**
 * Created by Dawid on 2016-12-24.
 */
public interface Command {
    void execute();
    void undo();
    void redo();
}
