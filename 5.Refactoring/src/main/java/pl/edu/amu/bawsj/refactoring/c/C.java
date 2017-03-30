package pl.edu.amu.bawsj.refactoring.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// znajdź błąd
public class C
{

    public static void main( String[] args ) throws IOException
    {
        checkIfNameExist( "Jan","Jan");
    }

    public static void checkIfNameExist(String... input ) throws IOException
    {

        if( input.length == 0 )
        {
            System.out.println( "Nie podałeś imienia!" );
            return;
        }

        InputStream inputStream;
        BufferedReader reader;
        //reader.mark(23587);
        boolean isNameFound = false;

        mainLoop:
        for( int i = 0; i < input.length; i++ )
        {
            //reader.reset();
            inputStream = C.class.getClassLoader().getResourceAsStream( "imieniny.txt" );
            reader = new BufferedReader( new InputStreamReader( inputStream ) );

            isNameFound = isNameFound(reader, isNameFound, input[i]);

            if( isNameFound )
            {
                System.out.println( input[ i ] + " istnieje!" );
            }

            else
            {
                System.out.println( input[ i ] + " nie istnieje!" );
            }

            reader.close();
            inputStream.close();
        }
    }

    private static boolean isNameFound(BufferedReader reader, boolean isNameFound, String s) throws IOException {
        while( true )
        {

            String readName = reader.readLine();


            if( readName == null )
            {
                break;
            }

            if( readName.trim().equals( s.trim() ) )
            {
                isNameFound = true;
                break;
            }
            else{
                isNameFound=false;
            }
        }
        return isNameFound;
    }
}
