import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.*;

public class Gui extends Application {

    Button applyButton, startButton, stopButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        OsuBoost bot = new OsuBoost();
        primaryStage.setTitle(Constants.APP_NAME);

        VBox root = new VBox();
        root.setPadding(new Insets(25, 25, 25, 25));

        // Title
        Text scenetitle = new Text("\t    "+Constants.APP_NAME);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.getChildren().add(scenetitle);

        // Resolution Label
        HBox row_resolution = new HBox();
        Label label_resolution = new Label("Resolution:\t");
        label_resolution.setStyle(Constants.BOLD_STYLE);;
        row_resolution.setPadding(new Insets(25, 0, 5, 0));

        // Resolution dropdown
        final ComboBox resolutionComboBox = new ComboBox();
        resolutionComboBox.getItems().addAll(Constants.RESOLUTIONS.keySet());
        resolutionComboBox.getSelectionModel().selectFirst();
        resolutionComboBox.setOnMouseClicked((event) -> setDisableButtons(false, true, true));
        row_resolution.getChildren().addAll(label_resolution, resolutionComboBox);

        // Keys Label
        HBox row_keys = new HBox();
        Label label_keys = new Label("\t  Keys:\t");
        label_keys.setStyle(Constants.BOLD_STYLE);
        row_keys.setPadding(new Insets(5, 0, 75, 0));

        // Keys dropdown
        final ComboBox keyComboBox = new ComboBox();
        keyComboBox.getItems().addAll(Constants.MANIA_KEYS.keySet());
        keyComboBox.getSelectionModel().selectFirst();
        keyComboBox.setOnMouseClicked((event) -> setDisableButtons(false, true, true));
        row_keys.getChildren().addAll(label_keys, keyComboBox);

        // Buttons row
        HBox button_row = new HBox();
        applyButton = new Button("✔ Apply");
        applyButton.setOnMouseClicked((event) -> {
            setDisableButtons(true, false, true);
            bot.apply(Constants.RESOLUTIONS.get(resolutionComboBox.getValue()), Constants.MANIA_KEYS.get(keyComboBox.getValue()));
        });

        startButton = new Button("⏩ Start");
        startButton.setOnMouseClicked((event) -> {
            setDisableButtons(true, true, false);
            try {
                bot.start();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        });
        startButton.setDisable(true);

        stopButton = new Button("■ Stop");
        stopButton.setOnMouseClicked((event) -> {
            setDisableButtons(true, false, true);
            bot.stop();
        });
        stopButton.setDisable(true);
        button_row.setSpacing(38);
        button_row.getChildren().addAll(applyButton, startButton, stopButton);

        root.getChildren().addAll(row_resolution, row_keys, button_row);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public void setDisableButtons(boolean disableApply, boolean disableStart, boolean disableStop) {
        applyButton.setDisable(disableApply);
        startButton.setDisable(disableStart);
        stopButton.setDisable(disableStop);
    }
}