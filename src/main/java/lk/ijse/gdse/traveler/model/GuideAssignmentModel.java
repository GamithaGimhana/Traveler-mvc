package lk.ijse.gdse.traveler.model;

import lk.ijse.gdse.traveler.dto.GuideAssignmentDTO;
import lk.ijse.gdse.traveler.dto.VehicleRentDTO;
import lk.ijse.gdse.traveler.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideAssignmentModel {
    public boolean saveGuideAssignment(GuideAssignmentDTO guideAssignmentDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into guide_assignment values (?,?,?,?,?,?)",
                guideAssignmentDTO.getRequestId(),
                guideAssignmentDTO.getGuideId(),
                guideAssignmentDTO.getTravelerId(),
                guideAssignmentDTO.getStartDate(),
                guideAssignmentDTO.getEndDate(),
                guideAssignmentDTO.isStatus()
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
