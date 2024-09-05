import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("helo");
        Scene stage = new Scene(label);

        primaryStage.setScene(stage);
        primaryStage.show();
    }

}
