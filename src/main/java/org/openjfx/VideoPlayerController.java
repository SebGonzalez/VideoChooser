package org.openjfx;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class VideoPlayerController {

    @FXML
    AnchorPane anchorePane;

    public void loadVideo(int step, int choice, double width, double height) {
        String path = MainApplication.PATH_TO_VIDEO + "video_" + step + "_" + choice + ".mp4";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(width);
        mediaView.setFitHeight(height);

        anchorePane.getChildren().setAll(mediaView);

        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();
            switchToIntro(step);
        });

        mediaView.requestFocus();
        mediaView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.S) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
                switchToIntro(step);
            }
        });


        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anchorePane);
        fadeTransition.setToValue(1);
        fadeTransition.setFromValue(0);
        fadeTransition.setOnFinished(event -> mediaPlayer.play());
        fadeTransition.play();
    }

    public void loadintro(int step, double width, double height) {
        String path = MainApplication.PATH_TO_VIDEO + "intro_" + step + ".mp4";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(width);
        mediaView.setFitHeight(height);

        DropShadow dropshadow = new DropShadow();
        dropshadow.setOffsetY(5.0);
        dropshadow.setOffsetX(5.0);
        mediaView.setEffect(dropshadow);
        anchorePane.getChildren().setAll(mediaView);

        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();

            if (step <= MainApplication.configurationLoader.getInteger(ConfigurationLoader.CHOICE_NUMBERS, 0)) {
                switchToChoice(step);
            } else {
                switchToHome();
            }
        });

        mediaView.requestFocus();
        mediaView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.S) {
                mediaPlayer.stop();
                mediaPlayer.dispose();

                if (step <= MainApplication.configurationLoader.getInteger(ConfigurationLoader.CHOICE_NUMBERS, 0)) {
                    switchToChoice(step);
                } else {
                    switchToHome();
                }
            }
        });

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anchorePane);
        fadeTransition.setToValue(1);
        fadeTransition.setFromValue(0);
        fadeTransition.setOnFinished(event -> mediaPlayer.play());
        fadeTransition.play();
    }

    public void switchToIntro(int step) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anchorePane);
        fadeTransition.setToValue(0);
        fadeTransition.setFromValue(1);
        fadeTransition.setOnFinished(event1 -> MainApplication.switchToIntro(step + 1));
        fadeTransition.play();
    }

    public void switchToChoice(int step) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anchorePane);
        fadeTransition.setToValue(0);
        fadeTransition.setFromValue(1);
        fadeTransition.setOnFinished(event1 -> MainApplication.switchToChoice(step));
        fadeTransition.play();
    }

    public void switchToHome() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anchorePane);
        fadeTransition.setToValue(0);
        fadeTransition.setFromValue(1);
        fadeTransition.setOnFinished(event1 -> MainApplication.switchToHome());
        fadeTransition.play();
    }
}
