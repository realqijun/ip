import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView image;
    //private ImageView background = new ImageView();

    public DialogBox(String s, Image i) {
        text = new Label(s);
        image = new ImageView(i);
        text.setWrapText(true);
        text.setPadding(new Insets(5, 10, 5, 10));
        image.setFitWidth(100.0);
        image.setFitHeight(100.0);
        image.setClip(new Circle(50, 50, 50.0));
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(text, image);
        //this.setBackground(new Background(new ImagePattern(image)));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getClientDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getServerDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}

