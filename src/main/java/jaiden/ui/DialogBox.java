package jaiden.ui;

import java.io.IOException;
import java.util.Collections;

import jaiden.command.CommandType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for dialog box.
     *
     * @param text Text to display.
     * @param img Image to display.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dialog.setText(text);
        this.displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        this.dialog.getStyleClass().add("reply-label");
    }

    /**
     * Changes dialog box style.
     *
     * @param commandType Type of command.
     */
    private void changeDialogStyle(CommandType commandType) {
        assert commandType != null;
        switch(commandType) {
        case ADDCOMMAND:
            this.dialog.getStyleClass().add("add-label");
            break;
        case CHANGEMARKCOMMAND:
            this.dialog.getStyleClass().add("marked-label");
            break;
        case DELETECOMMAND:
            this.dialog.getStyleClass().add("delete-label");
            break;
        case LISTCOMMAND:
            this.dialog.getStyleClass().add("list-label");
            break;
        case EXITCOMMAND:
            this.dialog.getStyleClass().add("exit-label");
            break;
        case ERRORCOMMAND:
            this.dialog.getStyleClass().add("error-label");
            break;
        default:
        }
    }

    /**
     * Gets user dialog box.
     *
     * @param text Text to display.
     * @param img Image to display.
     * @return User dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null;
        assert img != null;
        return new DialogBox(text, img);
    }

    /**
     * Gets jaiden dialog box.
     *
     * @param text Text to display.
     * @param img Image to display.
     * @param commandType Type of command.
     * @return Jaiden dialog box.
     */
    public static DialogBox getJaidenDialog(String text, Image img, CommandType commandType) {
        assert text != null;
        assert img != null;
        assert commandType != null;
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
