package pl.edu.amu.bawjs.design.adapter;

public class Adaptor
{
    private Adaptee adaptee;

    public Adaptor( Adaptee adaptee )
    {
        this.adaptee = adaptee;
    }

    public void adaptedMethod()
    {
        adaptee.method();
    }

}
