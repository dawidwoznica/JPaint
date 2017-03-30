package pl.edu.amu.bawsj.refactoring.a;

public class Processor
{
    private boolean close = false;

    public void setClose( boolean close )
    {
        this.close = close;
    }

    public Result process( String a )
    {
        if( !close )
        {
            return new Result( a );
        }

        return new Result( "null" );

    }
}
