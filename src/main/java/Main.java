import babygronk.BabyGronk;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application for GUI of BabyGronk.
 */
public class Main extends Application {

    private BabyGronk babyGronk = new BabyGronk("./data/BabyGronk.txt");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMinHeight(220);
        primaryStage.setMinWidth(417);
        MainWindow mainWindow = new MainWindow();
        Scene scene = new Scene(mainWindow);
        primaryStage.setScene(scene);
        mainWindow.setBabyGronk(babyGronk);
        primaryStage.setTitle("BabyGronk");
        primaryStage.show();
    }
}
