package co.istad.rathpanha.ecommerce.order.domain.dto;

import java.util.UUID;

public record CreateOrderResult(
    UUID orderId
) {
}
