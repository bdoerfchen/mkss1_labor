package hsb.mkss1.order_system.usecases.dtos

import java.time.LocalDateTime
import java.util.UUID

data class OrderDto(val id: UUID,
                    val items: List<ItemDto>,
                    val lumpSum: Int,
                    val checkoutTimestamp: LocalDateTime?,
                    val customerName: String)
