package pl.edu.amu.bawsj.junit;
import java.util.Stack;

public class MovingAverage
{
    private Stack <Double> stack = new Stack<>();

    MovingAverage(  )
    {

    }

    public void push( double val )
    {
        stack.push(val);
    }

    public double getAvg(int n)
    {
        if(stack.empty())
        {
            throw new IllegalStateException("Stos pusty");
        }
        else
        {
            double sum = 0;
            for (int i = 0; i < n; i++)
            {
                sum += stack.pop();
            }
            sum /= n;
            return sum;
        }
    }
}
