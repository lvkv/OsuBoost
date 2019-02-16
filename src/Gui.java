import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gui extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Constants.APP_NAME);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Title
        Text scenetitle = new Text(Constants.APP_NAME);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(scenetitle, 0,0, 2, 1);

        // Resolution Label
        Label label_resolution = new Label("Resolution:\t");
        gridPane.add(label_resolution, 0, 1);

        // Resolution dropdown
        final ComboBox resolutionComboBox = new ComboBox();
        resolutionComboBox.getItems().addAll(
                "1920 x 1080",
                "3840 x 2160",
                "2560 x 1440",
                "1280 x 720"
        );
        resolutionComboBox.getSelectionModel().selectFirst();
        gridPane.add(resolutionComboBox, 1,1);

        Button applyButton = new Button("Apply");
        gridPane.add(applyButton, 0, 2);

        Button startButton = new Button("Start");
        gridPane.add(startButton,0,3);

        Button stopButton = new Button("Stop");
        gridPane.add(stopButton,0,4);

        primaryStage.setScene(new Scene(gridPane, 300, 250));
        primaryStage.show();
    }
}