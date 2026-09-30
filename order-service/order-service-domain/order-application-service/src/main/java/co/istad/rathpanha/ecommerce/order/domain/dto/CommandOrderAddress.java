package co.istad.rathpanha.ecommerce.order.domain.dto;

public record CommandOrderAddress(
    String street,
    String postalCode,
    String city
) {
}
