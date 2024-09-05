import babygronk.BabyGronk;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class Main extends Application {

    private ScrollPane  scrollPane;
    private VBox vBox;
    private TextField   textField;
    private Button sendButton;
    private Scene   scene;
    private Image serverImage = new Image(this.getClass()
            .getResourceAsStream("/images/babygronk.jpg"));
    private Image clientImage = new Image(this.getClass()
            .getResourceAsStream("/images/user.jpg"));
    private BabyGronk babyGronk = new BabyGronk("./data/BabyGronk.txt");

    @Override
    public void start(Stage primaryStage) throws Exception {
        scrollPane = new ScrollPane();
        vBox = new VBox();
        scrollPane.setContent(vBox);

        textField = new TextField();
        sendButton = new Button("Send");

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        textField.setOnAction((event) -> {
            handleUserInput();
        });

        vBox.heightProperty().addListener((observable) -> {scrollPane.setVvalue(1.0);});

        AnchorPane pane = new AnchorPane();
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        pane.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        vBox.setPrefHeight(Region.USE_COMPUTED_SIZE);

        textField.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(textField , 1.0);
        AnchorPane.setBottomAnchor(textField, 1.0);
        pane.getChildren().addAll(scrollPane, textField, sendButton);

        scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void handleUserInput() {
        String input = textField.getText();
        String response = babyGronk.getResponse(input);
        vBox.getChildren().addAll(
                DialogBox.getClientDialog(input, clientImage),
                DialogBox.getServerDialog(response, serverImage)
        );
        textField.clear();
    }
}
