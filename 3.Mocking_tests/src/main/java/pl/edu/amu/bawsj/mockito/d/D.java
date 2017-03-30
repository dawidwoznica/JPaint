package pl.edu.amu.bawsj.mockito.d;

public class D {

    public static void main(String[] args) {
        System.out.println(new ResultProcessor(new UnpredictableResultProvider()).provide());
    }
}
