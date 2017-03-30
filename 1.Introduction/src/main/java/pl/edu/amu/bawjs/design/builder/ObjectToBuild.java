package pl.edu.amu.bawjs.design.builder;

public class ObjectToBuild
{
    private String propertyA;
    private String propertyB;

    public ObjectToBuild( Builder builder )
    {
        this.propertyA = builder.propertyA;
        this.propertyB = builder.propertyB;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private String propertyA;
        private String propertyB;

        public Builder propertyA( String propertyA )
        {
            this.propertyA = propertyA;
            return this;
        }

        public Builder propertyB( String propertyB )
        {
            this.propertyB = propertyB;
            return this;
        }

        public ObjectToBuild build()
        {
            return new ObjectToBuild( this );
        }
    }

    public static void main( String[] args )
    {
        ObjectToBuild obj = ObjectToBuild.builder().propertyA( "a" ).propertyA( "b" ).build();
    }
}
