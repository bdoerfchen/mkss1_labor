package de.hsbremen.mkss.shared.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
    private UUID id;
    private List<ItemDto> items;
    private int lumpSum;
    private LocalDateTime checkoutTimestamp;
    private OrderStatusEnumDto status;
    private String customerName;

    @Override
    public String toString() {
        return String.format("{id=%s, customer=%s, sum=%s}", id, customerName, lumpSum);
    }
}
