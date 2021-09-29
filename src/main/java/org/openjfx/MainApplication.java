package org.openjfx;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;

public class MainApplication extends Application {
    public static final String PATH_TO_VIDEO = "app/externalResources/videos/";
    public static final String PATH_TO_IMAGES = "app/externalResources/images/";
    public static final String PATH_TO_CONFIG = "app/externalResources/config.properties";
    private static final String CHOICE_PANEL = "choice_panel";
    private static final String VIDEO_PANEL = "video_panel";
    private static final String HOME_PANEL = "home_panel";
    private static HashMap<String, FXMLLoader> screenMap = new HashMap<>();
    private static Scene scene;
    public static ConfigurationLoader configurationLoader;

    @Override
    public void start(Stage stage) {
        configurationLoader = new ConfigurationLoader(PATH_TO_CONFIG);
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("views/home.fxml"));
        screenMap.put(HOME_PANEL, loader);

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();

        //ScenicView.show(stage.getScene());

        HomeController homeController = loader.getController();
        homeController.fitImage(scene.getWidth());
    }

    public static void switchToHome() {
        scene.setRoot(screenMap.get(HOME_PANEL).getRoot());

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(scene.getRoot());
        fadeTransition.setToValue(1);
        fadeTransition.setFromValue(0);
        fadeTransition.play();
    }

    public static void switchToChoice(int step) {
        if(!screenMap.containsKey(CHOICE_PANEL)) {
            screenMap.put(CHOICE_PANEL,  new FXMLLoader(MainApplication.class.getResource("views/choiceVideo.fxml")));
            try {
                scene.setRoot(screenMap.get(CHOICE_PANEL).load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            scene.setRoot(screenMap.get(CHOICE_PANEL).getRoot());
        }
        ((ChoiceVideoController)screenMap.get(CHOICE_PANEL).getController()).fitImage(step, scene.getWidth());

    }
    public static void switchToVideo(int step, int choice) {
        if(!screenMap.containsKey(VIDEO_PANEL)) {
            screenMap.put(VIDEO_PANEL,  new FXMLLoader(MainApplication.class.getResource("views/videoPlayer.fxml")));
            try {
                scene.setRoot(screenMap.get(VIDEO_PANEL).load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            scene.setRoot(screenMap.get(VIDEO_PANEL).getRoot());
        }
        ((VideoPlayerController)screenMap.get(VIDEO_PANEL).getController()).loadVideo(step, choice, scene.getWidth(), scene.getHeight());
    }

    public static void switchToIntro(int step) {
        if(!screenMap.containsKey(VIDEO_PANEL)) {
            screenMap.put(VIDEO_PANEL,  new FXMLLoader(MainApplication.class.getResource("views/videoPlayer.fxml")));
            try {
                scene.setRoot(screenMap.get(VIDEO_PANEL).load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            scene.setRoot(screenMap.get(VIDEO_PANEL).getRoot());
        }
        ((VideoPlayerController)screenMap.get(VIDEO_PANEL).getController()).loadintro(step, scene.getWidth(), scene.getHeight());
    }
}
