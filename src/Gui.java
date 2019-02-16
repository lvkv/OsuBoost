import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Gui extends Application {

    private Button applyButton, startButton, stopButton;
    private HBox row_heading, row_resolution, row_keys, button_row;
    private Label label_resolution, label_keys;
    private Image logo, deco_left, deco_right;
    private ImageView logoImage, heading_left, heading_right;
    private VBox base, root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        OsuBoost bot = new OsuBoost();
        primaryStage.setTitle(Constants.APP_NAME);
        base = new VBox();
        root = new VBox();
        root.setPadding(new Insets(5, 25, 25, 25));

        // Heading
        row_heading = new HBox();
        logo = new Image(new FileInputStream("src/logo.png"));
        deco_left = new Image(new FileInputStream("src/heading_left.png"));
        deco_right = new Image(new FileInputStream("src/heading_right.png"));
        logoImage = new ImageView(logo);
        heading_left = new ImageView(deco_left);
        heading_right = new ImageView(deco_right);
        logoImage.setFitHeight(100);
        logoImage.setFitWidth(100);
        heading_left.setFitHeight(100);
        heading_left.setFitWidth(100);
        heading_right.setFitHeight(100);
        heading_right.setFitWidth(100);
        row_heading.getChildren().addAll(heading_left, logoImage, heading_right);
        base.getChildren().add(row_heading);
        base.getChildren().add(root);

        // Resolution Label
        row_resolution = new HBox();
        label_resolution = new Label(Constants.RESOLUTION_TEXT);
        label_resolution.setStyle(Constants.BOLD_STYLE);;
        row_resolution.setPadding(new Insets(45, 0, 5, 0));

        // Resolution dropdown
        final ComboBox resolutionComboBox = new ComboBox();
        resolutionComboBox.getItems().addAll(Constants.RESOLUTIONS.keySet());
        resolutionComboBox.getSelectionModel().selectFirst();
        resolutionComboBox.setOnMouseClicked((event) -> setDisableButtons(false, true, true));
        row_resolution.getChildren().addAll(label_resolution, resolutionComboBox);

        // Keys Label
        row_keys = new HBox();
        label_keys = new Label(Constants.KEYS_TEXT);
        label_keys.setStyle(Constants.BOLD_STYLE);
        row_keys.setPadding(new Insets(5, 0, 55, 0));

        // Keys dropdown
        final ComboBox keyComboBox = new ComboBox();
        keyComboBox.getItems().addAll(Constants.MANIA_KEYS.keySet());
        keyComboBox.getSelectionModel().selectFirst();
        keyComboBox.setOnMouseClicked((event) -> setDisableButtons(false, true, true));
        row_keys.getChildren().addAll(label_keys, keyComboBox);

        // Buttons row
        button_row = new HBox();
        applyButton = new Button(Constants.APPLY_TEXT);
        applyButton.setOnMouseClicked((event) -> {
            setDisableButtons(true, false, true);
            bot.apply(Constants.RESOLUTIONS.get(resolutionComboBox.getValue()), Constants.MANIA_KEYS.get(keyComboBox.getValue()));
        });

        startButton = new Button(Constants.START_TEXT);
        startButton.setOnMouseClicked((event) -> {
            setDisableButtons(true, true, false);
            try {
                bot.start();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        });
        startButton.setDisable(true);

        stopButton = new Button(Constants.STOP_TEXT);
        stopButton.setOnMouseClicked((event) -> {
            setDisableButtons(true, false, true);
            bot.stop();
        });
        stopButton.setDisable(true);
        button_row.setSpacing(38);
        button_row.getChildren().addAll(applyButton, startButton, stopButton);

        root.getChildren().addAll(row_resolution, row_keys, button_row);
        primaryStage.setScene(new Scene(base, 300, 300));
        primaryStage.getIcons().add(new Image("logo.png"));
//        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void setDisableButtons(boolean disableApply, boolean disableStart, boolean disableStop) {
        applyButton.setDisable(disableApply);
        startButton.setDisable(disableStart);
        stopButton.setDisable(disableStop);
    }
}