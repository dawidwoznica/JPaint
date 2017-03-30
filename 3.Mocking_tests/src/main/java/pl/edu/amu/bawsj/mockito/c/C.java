package pl.edu.amu.bawsj.mockito.c;

public class C {
    public static void main(String[] args) {
        StringCreator creator = new StringCreator(new SoutPrinter());
        creator.create();
    }
}
