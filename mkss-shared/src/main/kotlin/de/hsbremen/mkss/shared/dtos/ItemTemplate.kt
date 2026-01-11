package de.hsbremen.mkss.shared.dtos

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    name = "Item Template",
    description = "Template to create a new item for an order"
)
data class ItemTemplate(
    @Schema(description = "Name of the product or service", example = "Bread")
    val name: String,

    @Schema(description = "Price for one instance of this item in cents", example = "100")
    val price: Int,

    @Schema(description = "The number of instances this item is purchased", example = "5")
    val quantity: Int
)
