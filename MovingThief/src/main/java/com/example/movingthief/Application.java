package com.example.movingthief;


import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;


public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("Thief.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent e) {
                try {
                    controller.Move(e.getCode());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        stage.initStyle(StageStyle.UTILITY);
        stage.setOnCloseRequest(e->e.consume());
        stage.setScene(scene);
        stage.setTitle("AlarmSystem GUI");


        stage.show();

    }

    public static void main(String[] args) {



        launch();

    }


}