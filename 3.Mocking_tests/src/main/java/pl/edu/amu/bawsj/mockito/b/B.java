package pl.edu.amu.bawsj.mockito.b;

public class B {
    public static void main(String[] args) {
        Data data = new Data("aaa", "bbb");
        DataChanger changer = new SetAllToXXXDataChanger();
        DataConcatenator dataConcatenator = new DataConcatenator(changer);
        System.out.println(dataConcatenator.concatenate(data));
    }
}
