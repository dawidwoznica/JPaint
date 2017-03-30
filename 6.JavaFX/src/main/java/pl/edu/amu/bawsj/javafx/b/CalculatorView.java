package pl.edu.amu.bawsj.javafx.b;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CalculatorView extends Application
{
    private CalculatorPresenter presenter;
    private Pane root;

    @Override
    public void start( Stage primaryStage ) throws Exception
    {
        try
        {
            root = FXMLLoader.load( getClass()
                .getResource( "/b.fxml" ) );

            for( int i = 0; i < 10; i++ )
            {
                Button lookup = (Button)root.lookup( "#num" + i );
                final int finalI = i;
                lookup.setOnMouseClicked( event -> presenter.numClicked( finalI ) );
            }
            root.lookup( "#multiplication" )
                .setOnMouseClicked( event -> presenter.multiplicationClicked() );
            root.lookup( "#addition" )
                .setOnMouseClicked( event -> presenter.additionClicked() );
            root.lookup( "#subtraction" )
                .setOnMouseClicked( event -> presenter.subtractionClicked() );
            root.lookup( "#division" )
                .setOnMouseClicked( event -> presenter.divisionClicked() );
            root.lookup( "#result" )
                .setOnMouseClicked( event -> presenter.resultClicked() );
            root.lookup( "#clear" )
                .setOnMouseClicked( event -> presenter.clearClicked() );
            /*root.lookup( "#dot" )
                    .setOnMouseClicked( event -> presenter.dotClicked() );*/

            root.setOnKeyPressed( new EventHandler<KeyEvent>()
            {
                @Override
                public void handle( KeyEvent keyEvent )
                {
// !!! nice!
                    switch ( keyEvent.getCode() )
                     {
                        case NUMPAD1:
                            presenter.numClicked( 1 );
                            break ;
                        case NUMPAD2:
                            presenter.numClicked( 2 );
                            break ;
                        case NUMPAD3:
                            presenter.numClicked( 3 );
                            break ;
                        case NUMPAD4:
                            presenter.numClicked( 4 );
                            break ;
                        case NUMPAD5:
                            presenter.numClicked( 5 );
                            break ;
                        case NUMPAD6:
                            presenter.numClicked( 6 );
                            break ;
                        case NUMPAD7:
                            presenter.numClicked( 7 );
                            break ;
                        case NUMPAD8:
                            presenter.numClicked( 8 );
                            break ;
                        case NUMPAD9:
                            presenter.numClicked( 9 );
                            break ;
                        case NUMPAD0:
                            presenter.numClicked( 0 );
                            break ;
                        case SUBTRACT:
                            presenter.subtractionClicked();
                            break ;
                        case ADD:
                            presenter.additionClicked();
                            break ;
                        case DIVIDE:
                            presenter.divisionClicked();
                            break ;
                        case MULTIPLY:
                            presenter.multiplicationClicked();
                            break ;
                        case DELETE:
                            presenter.clearClicked();
                            break ;
                        case ENTER:
                            presenter.resultClicked();
                            break ;
                        /*case COMMA:
                            presenter.dotClicked();
                            break ;*/
                    }
                }
            } );



            presenter = new CalculatorPresenter( this );

            Scene scene = new Scene( root, 300, 300 );
            primaryStage.setScene( scene );
            primaryStage.show();
        }
        catch( Exception e ) { e.printStackTrace(); }
    }

    public void showResult( String s )
    {
        ( ( TextField )( root.lookup( "#resultTextField" ) ) ).setText( s );
    }
}
