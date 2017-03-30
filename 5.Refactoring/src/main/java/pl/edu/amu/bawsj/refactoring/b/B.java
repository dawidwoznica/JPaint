package pl.edu.amu.bawsj.refactoring.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class B
{
    public static void main( String[] args ) throws IOException
    {
        InputStream inputStream = B.class.getClassLoader().getResourceAsStream( "gold.csv" );
        BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) );

        double average = Double.MIN_VALUE;

        average = getAverage(reader, average);
        System.out.println( average );

    }

    public static double getAverage(BufferedReader reader, double average) throws IOException {
        while( true )
        {
            String line = reader.readLine();
            if( line == null )
            {
                break;
            }
            String[] split = line.split( "," );
            average = Math.max( average, (Double.parseDouble( split[ 1 ] ) + Double.parseDouble( split[ 2 ] ) + Double
                .parseDouble( split[ 3 ] )) / 3.0 );
        }
        return average;
    }
}
