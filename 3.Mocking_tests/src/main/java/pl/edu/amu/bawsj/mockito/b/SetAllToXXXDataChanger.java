package pl.edu.amu.bawsj.mockito.b;

public class SetAllToXXXDataChanger implements DataChanger {

    @Override
    public void change(Data data) {
        data.setA("XXX");
        data.setB("XXX");
    }
}
