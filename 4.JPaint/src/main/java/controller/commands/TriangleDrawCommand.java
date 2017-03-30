package controller.commands;

import controller.HttpController;
import controller.JPaintViewController;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.Drawable;
import model.figures.Triangle;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import view.JPaintView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Dawid on 2016-12-24.
 */
public class TriangleDrawCommand implements Command {

    private int figureIndex;
    private int layerIndex;
    private JPaintViewController controller;
    private JPaintView view;
    private Drawable rememberedShape;


    public TriangleDrawCommand(JPaintView view, JPaintViewController controller, int figureIndex, int layerIndex){
        this.figureIndex = figureIndex;
        this.layerIndex = layerIndex;
        this.controller = controller;
        this.view = view;
    }


    @Override
    public void execute() {
        controller.getCommandStock().doneCommands.push(this);
        //controller.jsonPicture = controller.gson.toJson(controller.getPicture());
        //System.out.println(controller.jsonPicture);
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
