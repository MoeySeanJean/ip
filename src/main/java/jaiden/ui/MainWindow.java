package jaiden.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jaiden jaiden;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image jaidenImage = new Image(this.getClass().getResourceAsStream("/images/DaJaiden.png"));

    /**
     * Initializes the controller after its root element has been completely loaded.
     * Binds the vertical scroll value of the scroll pane to the height of the dialog container,
     * ensuring the scroll pane automatically scrolls to the bottom whenever new content is added.
     */
    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Jaiden instance.
     */
    public void setJaiden(Jaiden jaiden) {
        this.jaiden = jaiden;
    }

    /**
     * Shows welcome message.
     */
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getJaidenDialog("Hello! I'm Jaiden\nWhat can I do for you?", jaidenImage, "")
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jaiden's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jaiden.getResponse(input);
        String commandType = jaiden.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJaidenDialog(response, jaidenImage, commandType)
        );
        userInput.clear();
        if (commandType.equals("ExitCommand")) {
            Platform.exit();
        }
    }
}
