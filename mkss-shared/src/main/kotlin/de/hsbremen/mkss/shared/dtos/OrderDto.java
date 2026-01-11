package de.hsbremen.mkss.shared.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(
        name = "Order",
        description = "An order represents a list of items to buy"
)
public class OrderDto {
    @Schema(description = "Unique identifier of the order", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Schema(description = "List of items in this order")
    private List<ItemDto> items;

    @Schema(description = "The total sum of this order in cents", example = "5000")
    private int lumpSum;

    @Schema(description = "The timestamp for when this order was finally purchased", example = "2026-01-11T11:54:50.867Z")
    private LocalDateTime checkoutTimestamp;

    @Schema(description = "The processing status of this order", example = "ACCEPTED")
    private OrderStatusEnumDto status;

    @Schema(description = "The name of the customer that has created this order", example = "Max Mustermann")
    private String customerName;

    @Override
    public String toString() {
        return String.format("{id=%s, customer=%s, sum=%s}", id, customerName, lumpSum);
    }
}
