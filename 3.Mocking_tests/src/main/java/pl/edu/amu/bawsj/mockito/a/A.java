package pl.edu.amu.bawsj.mockito.a;

public class A {
    public static void main(String[] args) {
        RandomDataProvider dataProvider = new RandomDataProvider(0);
        Calculator calculator = new Calculator(dataProvider);
        System.out.println(calculator.calculate());
    }
}
