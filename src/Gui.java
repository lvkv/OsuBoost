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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws AWTException{
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
        resolutionComboBox.getItems().addAll(
                "1920 x 1080",
                "3840 x 2160",
                "2560 x 1440",
                "1280 x 720"
        );
        resolutionComboBox.getSelectionModel().selectFirst();
        row_resolution.getChildren().add(resolutionComboBox);
        root.getChildren().add(row_resolution);

        // Mania Keys radio button
        HBox radio_row = new HBox();
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("4 Key");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rb1.setPadding(new Insets(0, 25, 100, 0));
        radio_row.getChildren().add(rb1);
        RadioButton rb2 = new RadioButton("7 Key");
        rb2.setToggleGroup(group);
        radio_row.getChildren().add(rb2);
        root.getChildren().add(radio_row);

        // Buttons row
        HBox button_row = new HBox();
        Button applyButton = new Button("Apply");
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