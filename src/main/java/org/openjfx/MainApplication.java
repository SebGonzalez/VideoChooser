package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class MainApplication extends Application {
    private static final String CHOICE_PANEL = "choice_panel";
    private static final String VIDEO_PANEL = "video_panel";
    private static HashMap<String, FXMLLoader> screenMap = new HashMap<>();
    private static Scene scene;

    @Override
    public void start(Stage stage) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/home.fxml"));

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root, 900,600);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();

        HomeController homeController = loader.getController();
        homeController.fitImage(scene.getWidth());
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



    public static void main(String[] args) {
        launch(args);
    }
}
