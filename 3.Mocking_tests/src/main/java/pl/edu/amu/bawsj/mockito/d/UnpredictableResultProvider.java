package pl.edu.amu.bawsj.mockito.d;


import java.util.Random;

public class UnpredictableResultProvider implements ResultProvider {

    @Override
    public double provide() {
        return (100 / new Random().nextInt(2)) * 1.2334;
    }

}
