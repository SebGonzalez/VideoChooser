package org.openjfx;

import javafx.animation.FadeTransition;
import javafx.beans.property.ReadOnlyDoubleProperty;
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

    private static String PATH_TO_VIDEO = "app/videos/";

    @FXML
    AnchorPane anchorePane;

    public VideoPlayerController() {
    }

    public void loadVideo(int step, int choice, double width, double height) {
        String path = PATH_TO_VIDEO + "video_" + step + "_" + choice + ".mp4";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(width);
        mediaView.setFitHeight(height);

       /* DropShadow dropshadow = new DropShadow();
        dropshadow.setOffsetY(5.0);
        dropshadow.setOffsetX(5.0);
        mediaView.setEffect(dropshadow);*/
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
        String path = PATH_TO_VIDEO + "intro_" + step + ".mp4";
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
            if (step != 9) {
                mediaPlayer.dispose();
                switchToChoice(step);
            }
        });

        mediaView.requestFocus();
        mediaView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.S) {
                if (step != 9) {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                    switchToChoice(step);
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
        fadeTransition.setOnFinished(event1 -> Main.switchToIntro(step + 1));
        fadeTransition.play();
    }

    public void switchToChoice(int step) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anchorePane);
        fadeTransition.setToValue(0);
        fadeTransition.setFromValue(1);
        fadeTransition.setOnFinished(event1 -> Main.switchToChoice(step));
        fadeTransition.play();
    }


}
