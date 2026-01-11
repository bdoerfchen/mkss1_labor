package de.hsbremen.mkss.shared.dtos

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Representation of an order status", example = "ACCEPTED")
enum class OrderStatusEnumDto {
    EMPTY,
    IN_PREPARATION,
    COMMITED,
    ACCEPTED,
    REJECTED,
}