package pl.edu.amu.bawsj.javafx.b;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CalculatorPresenter
{
    private static final Logger LOG = LogManager.getLogger();

    private CalculatorView view;

    private StringProperty property = new SimpleStringProperty( "" );

    private CalculatorModel.Operation operation = null;
    private CalculatorModel.Num first = null;
    private CalculatorModel.Num second = null;
    //boolean dotdot = false;

    CalculatorPresenter( CalculatorView calculatorView )
    {
        this.view = calculatorView;
    }

    public void numClicked( int finalI )
    {
        LOG.info( finalI + " clicked" );
        property.setValue( property.getValue()+String.valueOf( finalI ) );
        view.showResult( String.valueOf( property.getValue() ) );

    }

    public void additionClicked()
    {
        LOG.info( "Addition clicked" );
        execute( new CalculatorModel.Addition() );
    }

    public void multiplicationClicked()
    {
        LOG.info( "Multiplication clicked" );
        execute( new CalculatorModel.Multiplication() );
    }

    public void subtractionClicked()
    {
        LOG.info( "Subtraction clicked" );
        execute( new CalculatorModel.Substraction() );
    }

    public void divisionClicked()
    {
        LOG.info( "Division clicked" );
        execute( new CalculatorModel.Division() );
    }

    public void resultClicked()
    {
        LOG.info( "Result clicked" );
        try
        {
            second = new CalculatorModel.Num( Double.parseDouble( property.getValue() ) );
            property.setValue( String.valueOf( operation.count( first, second ) ) );
            view.showResult( property.getValue() );
            first = null;
        }
// patrz: komentarz na dole. popracuj troche nad obslugiwaniem wyjatkow w Javie.
        catch ( Exception e ) { first = null;
        }
    }

    public void clearClicked()
    {
        LOG.info( "Clear clicked" );
        first = null;
        second = null;
        property.setValue( "" );
        view.showResult( "" );
    }

    /*public void dotClicked()
    {
        if( !dotdot )
        {
            LOG.info( "Dot clicked" );
            property.setValue( property.getValue() + "." );
            dotdot = true;
            view.showResult( String.valueOf( property.getValue() ) );
        }
    }*/

    private void execute( CalculatorModel.Operation oper )
    {
        try
        {
            operation = oper;
            if( first != null )
            {
                second = new CalculatorModel.Num( Double.parseDouble( property.getValue() ) );
                property.setValue( String.valueOf( operation.count( first, second ) ) );
                view.showResult( property.getValue() );
            }

            first = new CalculatorModel.Num( Double.parseDouble( property.getValue() ) );
            property.setValue( "" );

        }

        catch ( NumberFormatException e ) {
            // cos by sie przydalo tu wypisac.
            // to co tutaj zrobiles to nazywa sie "polykanie wyjatkow" i jest bardzo niemile widziane w kodzie - bo nie mamy zadnej infoamcji o tym ze cos sie wysypalo
        }
    }
}
