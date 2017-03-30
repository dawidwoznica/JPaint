package pl.edu.amu.bawsj.mockito.a;

public class Calculator {
    private DataProvider dataProvider;

    public Calculator(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public double calculate() {
        return (dataProvider.get() * dataProvider.get());
    }
}
