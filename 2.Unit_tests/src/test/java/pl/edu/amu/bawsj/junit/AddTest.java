package pl.edu.amu.bawsj.junit;

import org.junit.Assert;
import org.junit.Test;

public class AddTest
{
    @Test
    public void shouldAddTwoPositiveNumbers()
    {
        Add a = new Add();

        Assert.assertEquals(20, a.go(10, 10));
    }

    @Test
    public void shouldAddPositiveAndNegativeNumbers()
    {
        Add a = new Add();

        Assert.assertEquals(0, a.go(-10, 10));
    }

    @Test
    public void shouldHandleIntegerOverflow()
    {
        Add a = new Add();

        Assert.assertTrue(a.go(Integer.MAX_VALUE, 20)>0);
    }
}