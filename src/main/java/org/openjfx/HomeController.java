package org.openjfx;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.util.Duration;

public class HomeController {

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        imageView.setImage(new Image(getClass().getResource(("images/home_background.png")).toExternalForm()));
        imageView.getStyleClass().setAll("home");

        imageView.setOnMouseClicked(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.millis(1000));
            fadeTransition.setNode(imageView.getScene().getRoot());
            fadeTransition.setToValue(0);
            fadeTransition.setFromValue(1);
            fadeTransition.setOnFinished(event1 -> Main.switchToIntro(1));
            fadeTransition.play();
        });
    }

    public void fitImage(double width) {
        imageView.setFitWidth(width);
    }
}
