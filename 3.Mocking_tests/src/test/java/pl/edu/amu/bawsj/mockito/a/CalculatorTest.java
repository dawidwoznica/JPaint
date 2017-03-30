package pl.edu.amu.bawsj.mockito.a;

import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Assert;

public class CalculatorTest
{
    @Test
    public void shouldPerformSomeCalculationCorrectly()
    {
        DataProvider dataProvider = Mockito.mock(DataProvider.class);
        Calculator calculator = new Calculator(dataProvider);
        Mockito.when(dataProvider.get()).thenReturn(3.0);
        Assert.assertEquals(9.0,calculator.calculate(),0.003);
    }
}