package com.rtsgame;

import com.rtsgame.controllers.GameController;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application
{

    @Override
    public void start(Stage primaryStage) {

        GameController gameController = new GameController();
        gameController.startGame();
        gameController.getGameView().start(primaryStage); // Démarrer la vue
    
    }

    public static void main(String[] args) {
        launch(args);
    }

}