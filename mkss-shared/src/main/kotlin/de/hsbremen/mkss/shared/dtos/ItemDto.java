package de.hsbremen.mkss.shared.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(
        name = "Order Item",
        description = "Item represents a product or service a user has added to an order"
)
public class ItemDto {
    @Schema(description = "Unique identifier of the item", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID id;

    @Schema(description = "Name of the product or service", example = "Bread", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Price for one instance of this item in cents", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    private int price;

    @Schema(description = "The number of instances this item is purchased", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private int quantity;
}
