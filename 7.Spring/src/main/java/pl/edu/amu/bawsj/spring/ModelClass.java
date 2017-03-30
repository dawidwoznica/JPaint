package pl.edu.amu.bawsj.spring;

import java.util.List;

/**
 * Created by Bocian on 12.01.2017.
 */
public class ModelClass {
    private String a;
    private String b;
    private String c;
    private List<ModelClass2> modelClass2List;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public List<ModelClass2> getModelClass2List() {
        return modelClass2List;
    }

    public void setModelClass2List(List<ModelClass2> modelClass2List) {
        this.modelClass2List = modelClass2List;
    }
}
