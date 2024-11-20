package lk.ijse.gdse.traveler.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DetailController {

    @FXML
    private AnchorPane ancDetails;

    @FXML
    private Button btnAccomodation;

    @FXML
    private Button btnAttraction;

    @FXML
    private Button btnFood;

    @FXML
    private Button btnHealthcare;

    @FXML
    private Label lblDetails;

    @FXML
    void btnAccomodationOnAction(ActionEvent event) {

    }

    @FXML
    void btnAttractionOnAction(ActionEvent event) {

    }

    @FXML
    void btnFoodOnAction(ActionEvent event) {

    }

    @FXML
    void btnHealthcareOnAction(ActionEvent event) {

    }

    public void navigateTo(String fxmlPath) {
        try {
            ancDetails.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));

//  -------- Loaded anchor edges are bound to the content anchor --------
//      (1) Bind the loaded FXML to all edges of the content anchorPane
            load.prefWidthProperty().bind(ancDetails.widthProperty());
            load.prefHeightProperty().bind(ancDetails.heightProperty());

            ancDetails.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

}
