package pl.edu.amu.bawsj.mockito.c;

public class StringCreator {
    private SoutPrinter soutPrinter;

    StringCreator(SoutPrinter soutPrinter) {
        this.soutPrinter = soutPrinter;
    }

    public String create() {
        String s = "Hi there";
        soutPrinter.print(s);
        soutPrinter.print(s);
        return s;
    }
}
