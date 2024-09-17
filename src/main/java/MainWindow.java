import babygronk.BabyGronk;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;
    @FXML
    private TextField textField;
    @FXML
    private Button sendButton;

    private BabyGronk babyGronk;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/babygronk.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(vBox.heightProperty());
    }

    public void setBabyGronk(BabyGronk bg) {
        babyGronk = bg;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = textField.getText();
        String response = babyGronk.getResponse(input);
        vBox.getChildren().addAll(
                DialogBox.getClientDialog(input, userImage),
                DialogBox.getServerDialog(response, dukeImage)
        );
        textField.clear();
    }
}
