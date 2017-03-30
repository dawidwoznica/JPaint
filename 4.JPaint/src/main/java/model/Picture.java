package model;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dawid on 2016-12-11.
 */
public class Picture {

    private List<Layer> layers;
    private Layer currentLayer;
    private int currentLayerIndex;

    public Picture(){
        this.layers = new ArrayList<Layer>();
        Layer layer = new Layer();
        layers.add(layer);
        setCurrentLayerIndex();
        this.currentLayer = layers.get(getCurrentLayerIndex());
    }

    public void addLayer(){
        Layer newLayer = new Layer();
        layers.add(newLayer);
        setCurrentLayerIndex();
        currentLayer = layers.get(getCurrentLayerIndex());
    }

    public void moveUp(Layer layer){
        for(int i = 0; i < layers.size(); i++){
            if(layers.get(i).equals(layer)){
                if(i-1 >= 0) {
                    Layer cache = layers.get(i - 1);
                    layers.set(i - 1, layer);
                    layers.set(i, cache);
                    setCurrentLayerIndex();
                    currentLayer = layers.get(getCurrentLayerIndex());
                }
                else {
                    throw new NullPointerException();
                }
            }
        }
    }

    /*
    public void moveDown(Layer layer){
        for(int i = 0; i < layers.size(); i++){
            if(layers.get(i).equals(layer)){
                if(i+1 < layers.size()) {
                    Layer cache = layers.get(i + 1);
                    layers.set(i + 1, layer);
                    layers.set(i, cache);
                    setCurrentLayerIndex();
                    currentLayer = layers.get(getCurrentLayerIndex());
                }
                else{
                    throw new IndexOutOfBoundsException();
                }
            }
        }
    }
    */

    public void redraw(GraphicsContext gc){
        try{
            /*
            for(int i = 0; i < layers.size(); i++){
                for(int j = 0; j < layers.get(i).figures.size(); j++ ){
                    layers.get(i).figures.get(j).draw(gc);
                }
            }
            */
            for(int i = 0; i < layers.size(); i++){
                for(int j = 0; j < layers.get(layers.size()-i-1).figures.size(); j++){
                    layers.get(layers.size()-i-1).figures.get(j).draw(gc);
                }
            }

            /*
            for(int i = 0; i < figures.size(); i++){
                figures.get(i).draw(gc);
            }
            */
        }
        catch(NullPointerException e){
            System.out.println("NullPointer");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBounds");
        }

    }

    public List<Layer> getLayers() {
        return layers;
    }

    public int getNumberOfLayers(){
        return layers.size();
    }

    public Layer getCurrentLayer() {
        return currentLayer;
    }

    public void setCurrentLayer(Layer currentLayer) {
        this.currentLayer = currentLayer;
    }

    public void setCurrentLayerIndex() {
        for(Layer layer: layers){
            if(layer.equals(currentLayer)){
                currentLayerIndex = layers.indexOf(layer);
            }
        }
    }

    public int getCurrentLayerIndex() {
        return currentLayerIndex;
    }
}
