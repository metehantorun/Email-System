package com.example.emailsistemisonhali;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

//mail sisteminin çalıştığı sınıf
public class TSAApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TSAApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TSA Mail");
        stage.setScene(scene);

        Image icon=new Image("tsa.png");
        stage.getIcons().add(icon);

        stage.show();


    }
    public static void main(String[] args) {
        launch();    }
}



