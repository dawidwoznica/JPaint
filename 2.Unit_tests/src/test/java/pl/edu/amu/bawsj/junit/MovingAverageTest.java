package pl.edu.amu.bawsj.junit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class MovingAverageTest {

    // klasa ma wyliczac srednia z ostatnich N podanych liczb
    // jezeli uzytkownik nie poda zadnej liczby a zechce "srednia" to wyrzucany jest wyjatek: illegalstateexception

    @Test
    public void shouldSupportBigSetOfData() {
        MovingAverage movingAverage = new MovingAverage();
        for (long i = 0; i < 10000000l; i++) {
            movingAverage.push(1);
        }
        Assert.assertEquals(1, movingAverage.getAvg(100), 0.003);
    }

    @Test
    public void shouldHnadleNegativeNumbers() {
        MovingAverage movingAverage = new MovingAverage();
        movingAverage.push(-1);
        movingAverage.push(-10);
        Assert.assertEquals(-5.5, movingAverage.getAvg(2), 0.003);
    }

    @Test
    public void shouldThrowCorrectException() {
        try {
            MovingAverage movingAverage = new MovingAverage();
            movingAverage.getAvg(1);
            Assert.fail();
        }catch (Exception IllegalStateException) {}
    }
}
