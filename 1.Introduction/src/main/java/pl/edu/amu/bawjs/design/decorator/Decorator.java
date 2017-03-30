package pl.edu.amu.bawjs.design.decorator;

public class Decorator implements Component
{
    private final Component component;

    public Decorator( Component component )
    {
        this.component = component;
    }

    public void operation()
    {
        // decorate
        component.operation();
        // decorate
    }
}
