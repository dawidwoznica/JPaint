package pl.edu.amu.bawsj.refactoring.a;

public class A
{
    public static void main( String[] args )
    {
        Processor processor = new Processor();
        Result test1 = processor.process( "test1" );
        System.out.println( test1.getText() );
        processor.setClose( true );
        Result test2 = processor.process( "test2" );
        System.out.println( test2.getText() );
        processor.setClose( false );
        Result test3 = processor.process( "test3" );
        System.out.println( test3.getText() );
    }
}
