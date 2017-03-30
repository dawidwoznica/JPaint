package pl.edu.amu.bawsj.refactoring.a;

import org.junit.Test;
import org.junit.Assert;

public class ProcessorTest
{
    @Test
    public void shouldReturnText() throws Exception {
        Processor p = new Processor();
        Result test1 = p.process( "test1" );
        Assert.assertEquals("test1", test1.getText());
    }

    @Test
    public void shouldReturnStringNull() throws Exception {
        Processor p = new Processor();
        p.setClose( true );
        Result test2 = p.process( "test2" );
        Assert.assertEquals("null", test2.getText());
    }


    @Test
    public void shouldReturnNull() throws Exception {
        Processor p = new Processor();
        p.setClose( false );
        Result test2 = p.process( null );
        Assert.assertEquals(null, test2.getText());
    }
}