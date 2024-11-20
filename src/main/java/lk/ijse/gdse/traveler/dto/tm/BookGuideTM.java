package lk.ijse.gdse.traveler.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookGuideTM {
    private String requestId;
    private String travelerId;
    private String guideId;
    private Date startDate;
    private Date endDate;
    private Button removeBtn;
}
