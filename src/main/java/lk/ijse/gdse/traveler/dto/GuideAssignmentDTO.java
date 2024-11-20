package lk.ijse.gdse.traveler.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GuideAssignmentDTO {
    private String requestId;
    private String guideId;
    private String travelerId;
    private Date startDate;
    private Date endDate;
    private boolean status;
}
