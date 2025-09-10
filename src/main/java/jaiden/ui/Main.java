package jaiden.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final int STAGE_HEIGHT = 220;
    private static final int STAGE_WIDTH = 417;

    private final Jaiden jaiden = new Jaiden("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(STAGE_HEIGHT);
            stage.setMinWidth(STAGE_WIDTH);
            fxmlLoader.<MainWindow>getController().setJaiden(this.jaiden);
            stage.show();

            if (jaiden.hasLoadError()) {
                fxmlLoader.<MainWindow>getController().showLoadingError();
            }

            fxmlLoader.<MainWindow>getController().showWelcome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


