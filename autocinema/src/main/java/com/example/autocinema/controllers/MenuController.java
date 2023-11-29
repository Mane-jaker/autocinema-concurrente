package com.example.autocinema.controllers;

import com.example.autocinema.MenuApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {
    private static SimulatorController simulatorController;

    @FXML
    protected void onSimulationButtonClick(MouseEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(MenuApplication.class.getResource("simulator-view.fxml"));
        Parent root = loader.load();
        simulatorController = loader.getController();
        stage.getScene().setRoot(root);
    }

    @FXML
    protected void onExitButtonClick(MouseEvent event) {
        Platform.exit();
    }

}