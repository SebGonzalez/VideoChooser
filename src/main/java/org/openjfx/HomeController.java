package org.openjfx;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;

import static org.openjfx.MainApplication.PATH_TO_IMAGES;

public class HomeController {

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        imageView.getStyleClass().setAll("home");
        imageView.setImage(new Image(new File(PATH_TO_IMAGES + "home_background.png").toURI().toString()));

        imageView.setOnMouseClicked(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.millis(1000));
            fadeTransition.setNode(imageView.getScene().getRoot());
            fadeTransition.setToValue(0);
            fadeTransition.setFromValue(1);
            fadeTransition.setOnFinished(event1 -> MainApplication.switchToIntro(1));
            fadeTransition.play();
        });
    }

    public void fitImage(double width) {
        imageView.setFitWidth(width);
    }
}
