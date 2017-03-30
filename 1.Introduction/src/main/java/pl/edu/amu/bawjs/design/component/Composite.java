package pl.edu.amu.bawjs.design.component;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component
{
    private List<Component> children = new ArrayList<>();

    @Override

    public void operation()
    {
        children.forEach( c -> c.operation() );
    }

    public void add( Component component )
    {
        children.add( component );
    }

    public void remove( Component component )
    {
        children.remove( component );
    }

}
