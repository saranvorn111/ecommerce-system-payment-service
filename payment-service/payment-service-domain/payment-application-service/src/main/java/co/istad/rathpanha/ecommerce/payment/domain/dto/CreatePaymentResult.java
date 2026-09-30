package co.istad.rathpanha.ecommerce.payment.domain.dto;

import co.istad.rathpanha.ecommerce.domain.valueobject.PaymentStatus;

import java.util.UUID;

public record CreatePaymentResult(
    UUID paymentId,
    PaymentStatus paymentStatus
) {
}
