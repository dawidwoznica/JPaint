package pl.edu.amu.bawsj.javafx.a;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class A extends Application
{
    public static void main( String[] args )
    {
        launch( args );
    }

    @Override
    public void start( Stage primaryStage ) throws Exception
    {
            Pane root = FXMLLoader.load( A.class.getResource("/a.fxml" ) );
            Scene scene = new Scene( root, 400, 400 );
            primaryStage.setScene( scene );
            primaryStage.show();
    }
}
