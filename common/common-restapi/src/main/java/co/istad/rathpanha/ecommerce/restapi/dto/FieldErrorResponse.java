package co.istad.rathpanha.ecommerce.restapi.dto;

public record FieldErrorResponse(
    String field,
    String code,
    String reason
) {
}
