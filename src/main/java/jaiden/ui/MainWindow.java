package jaiden.ui;

import jaiden.command.CommandType;
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
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Injects the Jaiden instance.
     */
    public void setJaiden(Jaiden jaiden) {
        assert jaiden != null;
        this.jaiden = jaiden;
    }

    /**
     * Shows welcome message.
     */
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getJaidenDialog("Hello! I'm Jaiden\nWhat can I do for you?", this.jaidenImage,
                        CommandType.NULLCOMMAND)
        );
    }

    /**
     * Shows loading error.
     */
    public void showLoadingError() {
        dialogContainer.getChildren().addAll(
                DialogBox.getJaidenDialog("The data file is corrupted (Content not in the expected format).",
                        this.jaidenImage, CommandType.ERRORCOMMAND)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jaiden's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        assert !input.isEmpty();
        String response = this.jaiden.getResponse(input);
        assert response != null;
        CommandType commandType = this.jaiden.getCommandType();
        assert commandType != null;
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getJaidenDialog(response, this.jaidenImage, commandType)
        );
        this.userInput.clear();
        if (commandType.equals(CommandType.EXITCOMMAND)) {
            Platform.exit();
        }
    }
}
