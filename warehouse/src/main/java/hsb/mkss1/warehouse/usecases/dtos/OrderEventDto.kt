package hsb.mkss1.warehouse.usecases.dtos

import java.time.LocalDateTime
import java.util.UUID

data class OrderEventDto(val id: UUID,
                         val items: List<ItemDto>,
                         val lumpSum: Int,
                         val checkoutTimestamp: LocalDateTime?,
                         val status: OrderStatusEnum,
                         val customerName: String)
