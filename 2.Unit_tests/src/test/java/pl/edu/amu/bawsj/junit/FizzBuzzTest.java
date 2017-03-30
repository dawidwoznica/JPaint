package pl.edu.amu.bawsj.junit;

import org.junit.Assert;
import org.junit.Test;

public class FizzBuzzTest
{
    @Test
    public void testTest()
    {
        FizzBuzz fb = new FizzBuzz();
        Assert.assertEquals("Fizz",fb.returnCos(3));
    }
    @Test
    public void shouldDivideByFive()
    {
        FizzBuzz fb = new FizzBuzz();
        Assert.assertEquals("Buzz",fb.returnCos(5));
    }
    @Test
    public void shouldDivideByThreeAndFive()
    {
        FizzBuzz fb = new FizzBuzz();
        Assert.assertEquals("FizzBuzz",fb.returnCos(15));
    }
    @Test
    public void shouldDivideByOther()
    {
        FizzBuzz fb = new FizzBuzz();
        Assert.assertEquals("4",fb.returnCos(4));
    }
}