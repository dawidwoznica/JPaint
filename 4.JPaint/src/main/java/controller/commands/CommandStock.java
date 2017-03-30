package controller.commands;

import controller.JPaintViewController;
import view.JPaintView;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Dawid on 2016-12-24.
 */
public class CommandStock {

    //JPaintView view;
    //JPaintViewController controller;

    Stack<Command> doneCommands;
    Stack<Command> undoneCommands;

    public CommandStock(){
        //this.view = view;
        //this.controller = controller;
        this.doneCommands = new Stack<>();
        this.undoneCommands = new Stack<>();
    }

    public void getHistory(){

    }

    public void undo(){
        try {
            Command toUnDo = doneCommands.pop();
            toUnDo.undo();
            undoneCommands.push(toUnDo);
        }
        catch (EmptyStackException e){
            System.out.println("There's no commands to undo.");
        }

    }

    public void redo(){
        try {
            Command toReDo = undoneCommands.pop();
            toReDo.redo();
            doneCommands.push(toReDo);
        }
        catch (EmptyStackException e){
            System.out.println("There's no commands to redo.");
        }

    }

}
