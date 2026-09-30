package co.istad.rathpanha.ecommerce.order.restapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderAddressRequest(
    @NotNull
    @Size(min = 1, max = 20)
    String street,
    @NotNull
    @Size(min = 1, max = 10)
    String postalCode,
    @NotNull
    @Size(min = 1, max = 30)
    String city
) {
}
