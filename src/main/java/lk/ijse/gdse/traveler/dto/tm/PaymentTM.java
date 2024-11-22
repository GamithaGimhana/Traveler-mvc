package lk.ijse.gdse.traveler.dto.tm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTM {
    private String paymentId;
    private String travelerId;
    private String requestId;
    private double amount;
    private double remaining;
    private LocalDate paymentDate;
    private String paymentMethod;
}
