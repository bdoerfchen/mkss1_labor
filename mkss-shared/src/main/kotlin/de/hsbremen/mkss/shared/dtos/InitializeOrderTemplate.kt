package de.hsbremen.mkss.shared.dtos

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    name = "Initialize Order Template",
    description = "Template to create a new order"
)
data class InitializeOrderTemplate(
    @Schema(description = "Name of the customer that initializes the order", example = "Max Mustermann")
    val customerName: String
)

