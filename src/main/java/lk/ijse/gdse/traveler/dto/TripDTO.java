package lk.ijse.gdse.traveler.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TripDTO {
    private String tripId;
    private String requestId;
    private String guideId;
    private String driverId;
    private String vehicleId;
    private Date startDate;
    private Date endDate;
    private double cost;
    private boolean tripStatus;
}
