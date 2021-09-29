package org.openjfx;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.openjfx.MainApplication.PATH_TO_IMAGES;

public class ChoiceVideoController {

    @FXML GridPane gridPane;
    @FXML ImageView imageView;
    @FXML ImageView imageView2;
    @FXML Label label1;
    @FXML Label label2;

    @FXML
    public void initialize() {
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);
    }

    public void fitImage(int step, double width) {
        label1.setText(MainApplication.configurationLoader.getTitle(step, 1));
        label2.setText(MainApplication.configurationLoader.getTitle(step, 2));

        imageView.setFitWidth(width / 2 - 50);
        imageView2.setFitWidth(width / 2 - 50);

        imageView.setImage(new Image(new File(PATH_TO_IMAGES + step + "_1.png").toURI().toString()));
        imageView2.setImage(new Image(new File(PATH_TO_IMAGES + step + "_2.png").toURI().toString()));

        imageView.setOnMouseClicked(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.millis(1000));
            fadeTransition.setNode(imageView.getScene().getRoot());
            fadeTransition.setToValue(0);
            fadeTransition.setFromValue(1);
            fadeTransition.setOnFinished(event1 -> MainApplication.switchToVideo(step, 1));
            fadeTransition.play();
        });
        imageView2.setOnMouseClicked(event -> MainApplication.switchToVideo(step, 2));

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(imageView.getScene().getRoot());
        fadeTransition.setToValue(1);
        fadeTransition.setFromValue(0);
        fadeTransition.play();
    }
}
