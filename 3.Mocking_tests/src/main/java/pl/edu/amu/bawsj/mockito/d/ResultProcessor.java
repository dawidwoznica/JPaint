package pl.edu.amu.bawsj.mockito.d;

public class ResultProcessor {

    private ResultProvider provider;

    ResultProcessor(ResultProvider provider) {
        this.provider = provider;
    }

    public String provide() {
        return provider.provide() + " " + provider.provide();
    }

}
