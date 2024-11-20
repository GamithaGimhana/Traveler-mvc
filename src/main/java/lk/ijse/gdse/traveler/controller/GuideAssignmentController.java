package lk.ijse.gdse.traveler.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import lk.ijse.gdse.traveler.dto.tm.BookGuideTM;
import lk.ijse.gdse.traveler.model.TravelerModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuideAssignmentController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            System.out.println("Initializing the page...");

            dateSet(dateStart);
            dateSet(dateEnd);

            // Disable components initially
            dateEnd.setDisable(true);
            cmbGuideId.setDisable(true);

            setCellValues();

            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load data during initialization: " + e.getMessage()).show();
        }
    }

    @FXML
    private AnchorPane ancGuideAssignment;

    @FXML
    private Button btnDelete;

    @FXML
    private ComboBox<?> cmbGuideId;

    @FXML
    private ComboBox<?> cmbLang;

    @FXML
    private ComboBox<String> cmbTravelerId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colEndDate;

    @FXML
    private TableColumn<?, ?> colGuideId;

    @FXML
    private TableColumn<?, ?> colRequestId;

    @FXML
    private TableColumn<?, ?> colStartDate;

    @FXML
    private TableColumn<?, ?> colTravelerId;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private DatePicker dateStart;

    @FXML
    private Label lblGuideName;

    @FXML
    private Label lblRequestId;

    @FXML
    private Label lblTravelerName;

    @FXML
    private Label requestDate;

    @FXML
    private TableView<BookGuideTM> tblBooking;

    private String requestId;

    private final TravelerModel travelerModel = new TravelerModel();

    private final ObservableList<BookGuideTM> bookGuideTMS = FXCollections.observableArrayList();


    @FXML
    void btnBookGuideOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void cmbGuideOnAction(ActionEvent event) {

    }

    @FXML
    void cmbLangOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTravelerOnAction(ActionEvent event) {

    }

    @FXML
    void dateEndOnAction(ActionEvent event) {

    }

    @FXML
    void dateStartOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    public void setRequestId(String requestId) {
        System.out.println("Request ID received: " + requestId);
        this.requestId = requestId;
        lblRequestId.setText(requestId);
    }

    private void setCellValues() {
        try {
            System.out.println("Setting table column values...");

            colRequestId.setCellValueFactory(new PropertyValueFactory<>("requestId"));
            colTravelerId.setCellValueFactory(new PropertyValueFactory<>("travelerId"));
            colGuideId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
            colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            colAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));
            tblBooking.setItems(bookGuideTMS);

            System.out.println("Table column values set successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error setting table column values: " + e.getMessage()).show();
        }
    }

    private void refreshPage() throws SQLException {
        System.out.println("Refreshing the page...");

        try {
            requestDate.setText(LocalDate.now().toString());

            System.out.println("Loading traveler IDs...");
            loadTravelerIds();

            cmbTravelerId.getSelectionModel().clearSelection();
            lblTravelerName.setText("");

            bookGuideTMS.clear();
            tblBooking.refresh();

            System.out.println("Page refreshed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error refreshing the page: " + e.getMessage()).show();
        }
    }

    private void loadTravelerIds() throws SQLException {
        System.out.println("Loading traveler IDs...");

        try {
            ArrayList<String> travelerIds = travelerModel.getAllTravelerIds();
            ObservableList<String> travelerIdsObservableList = FXCollections.observableArrayList(travelerIds);
            cmbTravelerId.setItems(travelerIdsObservableList);

            System.out.println("Traveler IDs loaded: " + travelerIdsObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading traveler IDs: " + e.getMessage()).show();
        }
    }

    private void dateSet(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate) {
                return localDate == null ? "" : dateFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String string) {
                return string == null || string.isEmpty() ? null : LocalDate.parse(string, dateFormatter);
            }
        });

        // Disable all dates today or earlier
        LocalDate today = LocalDate.now();

        datePicker.setDayCellFactory(dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(today)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // Optional: Highlight disabled dates
                }
            }
        });
    }
}
