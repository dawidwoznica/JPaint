package pl.edu.amu.bawjs.design.factory_method;

public class ConcreteCreator implements Creator
{

    @Override
    public Product factoryMethod()
    {
        return new ConcreteProduct();
    }

    private class ConcreteProduct implements Product
    {
    }
}
