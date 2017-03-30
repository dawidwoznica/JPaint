package pl.edu.amu.bawsj.javafx.b;

public class CalculatorModel
{
    public static class Num
    {
        private double value;

        public Num( double value ) { this.value = value; }

        public double getValue() { return value; }
    }

    public interface Operation
    {
        double count( Num num1,  Num num2 );
    }

    public static class Multiplication implements Operation
    {
        public double count( Num num1, Num num2 )
        {
            return num1.value * num2.value;
        }
    }

    public static class Division implements Operation
    {
        public double count( Num num1, Num num2 )
        {
            return num1.value / num2.value;
        }
    }

    public static class Addition implements Operation
    {
        public double count( Num num1, Num num2 )
        {
            return num1.value + num2.value;
        }
    }

    public static class Substraction implements Operation
    {
        public double count( Num num1, Num num2 )
        {
            return num1.value - num2.value;
        }
    }
}
