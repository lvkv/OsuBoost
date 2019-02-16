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
import java.util.HashMap;

public class Gui extends Application {

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
        Text scenetitle = new Text(Constants.APP_NAME);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.getChildren().add(scenetitle);

        // Resolution Label
        HBox row_resolution = new HBox();
        Label label_resolution = new Label("Resolution:\t");
        row_resolution.setPadding(new Insets(5, 0, 5, 0));
        row_resolution.getChildren().add(label_resolution);

        // Resolution dropdown
        final ComboBox resolutionComboBox = new ComboBox();
        resolutionComboBox.getItems().addAll(Constants.RESOLUTIONS.keySet());
        resolutionComboBox.getSelectionModel().selectFirst();
        row_resolution.getChildren().add(resolutionComboBox);
        root.getChildren().add(row_resolution);

        // Keys Label
        HBox row_keys = new HBox();
        Label label_keys = new Label("Keys:\t\t");
        row_keys.setPadding(new Insets(5, 0, 100, 0));
        row_keys.getChildren().add(label_keys);

        // Keys dropdown
        final ComboBox keyComboBox = new ComboBox();
        keyComboBox.getItems().addAll(Constants.MANIA_KEYS.keySet());
        keyComboBox.getSelectionModel().selectFirst();
        row_keys.getChildren().add(keyComboBox);
        root.getChildren().add(row_keys);

        // Buttons row
        HBox button_row = new HBox();
        Button applyButton = new Button("Apply");
        applyButton.setOnAction((event) -> bot.apply(Constants.RESOLUTIONS.get(resolutionComboBox.getValue()), Constants.MANIA_KEYS.get(keyComboBox.getValue())));
        button_row.getChildren().add(applyButton);

        Button startButton = new Button("Start");
        startButton.setOnAction((event) -> {
            try {
                bot.start();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        });
        button_row.getChildren().add(startButton);

        Button stopButton = new Button("Stop");
        stopButton.setOnAction((event) -> bot.stop());
        button_row.getChildren().add(stopButton);
        root.getChildren().add(button_row);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}