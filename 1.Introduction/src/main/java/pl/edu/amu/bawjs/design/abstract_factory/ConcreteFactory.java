package pl.edu.amu.bawjs.design.abstract_factory;

public class ConcreteFactory implements AbstractFactory
{
    @Override
    public ProductA createProductA()
    {
        return new ConcreteProductA();
    }

    @Override
    public ProductB createProductB()
    {
        return new ConcreteProductB();
    }

    private class ConcreteProductB implements ProductB
    {
    }

    private class ConcreteProductA implements ProductA
    {
    }
}
