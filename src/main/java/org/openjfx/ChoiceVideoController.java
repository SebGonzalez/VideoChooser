package org.openjfx;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ChoiceVideoController {

    //@FXML MediaView mediaView1;
    // @FXML MediaView mediaView2;
    @FXML GridPane gridPane;
    @FXML ImageView imageView;
    @FXML ImageView imageView2;
    @FXML Label label1;
    @FXML Label label2;

    private static final Map<Integer, String[]> titles;

    static {
        titles = new HashMap<>();
        titles.put(1, new String[]{"DJ Mira", "Jeu de mots Mira"});
        titles.put(2, new String[]{"Ribambelle de blagues", "Ce reve bleu"});
        titles.put(3, new String[]{"Les retrouvailles de guigui (avec Marion)", "La suprise (à ne pas choisir)"});
        titles.put(4, new String[]{"La video de Sainz", "La video d'Alicia"});
        titles.put(5, new String[]{"Les checks originaux de Sopheak", "La tentative de seduction"});
        titles.put(6, new String[]{"La danse sautillante", "La choregraphique synchronisee"});
        titles.put(7, new String[]{"La visite dans la serre", "Autre chose qui n'a rien à voir"});
        titles.put(8, new String[]{"Le show de la pomme magique", "La date en rap"});
    }

    public ChoiceVideoController() {
    }

    @FXML
    public void initialize() {
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);
    }

    public void fitImage(int step, double width) {
        label1.setText(titles.get(step)[0]);
        label2.setText(titles.get(step)[1]);

        imageView.setFitWidth(width / 2 - 50);
        imageView2.setFitWidth(width / 2 - 50);

        imageView.setImage(new Image(getClass().getResource(("images/" + step + "_1.png")).toExternalForm()));
        imageView2.setImage(new Image(getClass().getResource(("images/" + step + "_2.png")).toExternalForm()));

        imageView.setOnMouseClicked(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.millis(1000));
            fadeTransition.setNode(imageView.getScene().getRoot());
            fadeTransition.setToValue(0);
            fadeTransition.setFromValue(1);
            fadeTransition.setOnFinished(event1 -> Main.switchToVideo(step, 1));
            fadeTransition.play();
        });
        imageView2.setOnMouseClicked(event -> Main.switchToVideo(step, 2));

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(imageView.getScene().getRoot());
        fadeTransition.setToValue(1);
        fadeTransition.setFromValue(0);
        fadeTransition.play();
    }
}
