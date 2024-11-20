package lk.ijse.gdse.traveler.model;

import lk.ijse.gdse.traveler.dto.TripDTO;
import lk.ijse.gdse.traveler.dto.VehicleRentDTO;
import lk.ijse.gdse.traveler.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripModel {
    public boolean saveTrip(TripDTO tripDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into trip values (?,?,?,?,?,?,?,?,?)",
                tripDTO.getTripId(),
                tripDTO.getRequestId(),
                tripDTO.getGuideId(),
                tripDTO.getDriverId(),
                tripDTO.getVehicleId(),
                tripDTO.getStartDate(),
                tripDTO.getEndDate(),
                tripDTO.getCost(),
                tripDTO.isTripStatus()
        );
    }

    public boolean checkRequestIdExists(String requestId) throws SQLException {
        String query = "SELECT COUNT(*) FROM request WHERE request_id = ?";
        ResultSet rs = CrudUtil.execute(query, requestId);
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }
}
