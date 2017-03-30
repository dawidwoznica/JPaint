package pl.edu.amu.bawsj.mockito.a;

import java.util.Random;

public class RandomDataProvider implements DataProvider {

    private Random random;

    public RandomDataProvider(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public double get() {
        return random.nextDouble();
    }
}
