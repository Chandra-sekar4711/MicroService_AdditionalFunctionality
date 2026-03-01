package org.example.DTO.Payment;

import lombok.Data;


@Data
public class PaymentModel {


    private Long paymentId;

    private String orderId;

    private String paymentStatus;

    private String NotificationStatus;
}