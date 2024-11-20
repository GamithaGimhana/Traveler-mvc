package lk.ijse.gdse.traveler.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import lk.ijse.gdse.traveler.dto.tm.BookTripTM;
import lk.ijse.gdse.traveler.model.TravelerModel;
import lk.ijse.gdse.traveler.model.VehicleModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TripController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            System.out.println("Initializing the page...");

            dateSet(dateStart);
            dateSet(dateEnd);

            // Disable components initially
            dateEnd.setDisable(true);
            cmbVModel.setDisable(true);
            cmbVId.setDisable(true);

            setCellValues();

            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load data during initialization: " + e.getMessage()).show();
        }
    }

    @FXML
    private Label assignmentDate;

    @FXML
    private Button btnAddToBooking;

    @FXML
    private ComboBox<?> cmbDriverId;

    @FXML
    private ComboBox<?> cmbGuideId;

    @FXML
    private ComboBox<?> cmbLang;

    @FXML
    private ComboBox<String> cmbTravelerId;

    @FXML
    private ComboBox<String> cmbVId;

    @FXML
    private ComboBox<String> cmbVModel;

    @FXML
    private ComboBox<String> cmbVType;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colEndDate;

    @FXML
    private TableColumn<?, ?> colGId;

    @FXML
    private TableColumn<?, ?> colRId;

    @FXML
    private TableColumn<?, ?> colStartDate;

    @FXML
    private TableColumn<?, ?> colTId;

    @FXML
    private TableColumn<?, ?> colVId;

    @FXML
    private TableColumn<?, ?> colTripId;

    @FXML
    private TableColumn<?, ?> colDId;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private DatePicker dateStart;

    @FXML
    private Label lblDriverName;

    @FXML
    private Label lblGuideName;

    @FXML
    private Label lblLPlate;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblTravelerName;

    @FXML
    private Label lblTripId;

    @FXML
    private Label lblRentalId;

    @FXML
    private TableView<BookTripTM> tblBooking;

    private String requestId;

    private final TravelerModel travelerModel = new TravelerModel();
    private final VehicleModel vehicleModel = new VehicleModel();

    private final ObservableList<BookTripTM> bookTripTMS = FXCollections.observableArrayList();

    @FXML
    void btnAddToBookingOnAction(ActionEvent event) {

    }

    @FXML
    void btnBookTripOnAction(ActionEvent event) {

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
    void cmbVIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbVModelOnAction(ActionEvent event) {

    }

    @FXML
    void cmbVTypeOnAction(ActionEvent event) {

    }

    @FXML
    void dateEndOnAction(ActionEvent event) {

    }

    @FXML
    void dateStartOnAction(ActionEvent event) {

    }

    public void setRequestId(String requestId) {
        System.out.println("Request ID received: " + requestId);
        this.requestId = requestId;
        lblRentalId.setText(requestId);
    }

    private void setCellValues() {
        try {
            System.out.println("Setting table column values...");

            colTripId.setCellValueFactory(new PropertyValueFactory<>("tripId"));
            colRId.setCellValueFactory(new PropertyValueFactory<>("requestId"));
            colTId.setCellValueFactory(new PropertyValueFactory<>("travelerId"));
            colVId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
            colDId.setCellValueFactory(new PropertyValueFactory<>("driverId"));
            colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("cost"));
            colAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));
            tblBooking.setItems(bookTripTMS);

            System.out.println("Table column values set successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error setting table column values: " + e.getMessage()).show();
        }
    }

    private void refreshPage() throws SQLException {
        System.out.println("Refreshing the page...");

        try {
            assignmentDate.setText(LocalDate.now().toString());

            System.out.println("Loading traveler IDs...");
            loadTravelerIds();
            System.out.println("Loading vehicle types...");
            loadVehicleTypes();


            cmbTravelerId.getSelectionModel().clearSelection();
            cmbVId.getSelectionModel().clearSelection();
            cmbVType.getSelectionModel().clearSelection();
            cmbVModel.getSelectionModel().clearSelection();
            lblTravelerName.setText("");
            lblLPlate.setText("");

            bookTripTMS.clear();
            tblBooking.refresh();

            System.out.println("Page refreshed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error refreshing the page: " + e.getMessage()).show();
        }
    }

    private void loadVehicleIds(String selectedVehicleModel) throws SQLException {
        System.out.println("Loading vehicle IDs for model: " + selectedVehicleModel);

        try {
            ArrayList<String> vehicleIds = vehicleModel.getAllVehicleIds(selectedVehicleModel);
            ObservableList<String> vehicleIdObservableList = FXCollections.observableArrayList(vehicleIds);
            cmbVId.setItems(vehicleIdObservableList);

            System.out.println("Vehicle IDs loaded: " + vehicleIdObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading vehicle IDs: " + e.getMessage()).show();
        }
    }

    private void loadVehicleModels(String selectedVehicleType) throws SQLException {
        System.out.println("Loading vehicle models for type: " + selectedVehicleType);

        try {
            ArrayList<String> vehicleModels = vehicleModel.getAllVehicleModels(selectedVehicleType);
            ObservableList<String> vehicleModelObservableList = FXCollections.observableArrayList(vehicleModels);
            cmbVModel.setItems(vehicleModelObservableList);

            System.out.println("Vehicle models loaded: " + vehicleModelObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading vehicle models: " + e.getMessage()).show();
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

    private void loadVehicleTypes() throws SQLException {
        System.out.println("Loading vehicle types...");

        try {
            ArrayList<String> vehicleTypes = vehicleModel.getAllVehicleTypes();
            ObservableList<String> vehicleTypeObservableList = FXCollections.observableArrayList(vehicleTypes);
            cmbVType.setItems(vehicleTypeObservableList);

            System.out.println("Vehicle types loaded: " + vehicleTypeObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading vehicle types: " + e.getMessage()).show();
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
