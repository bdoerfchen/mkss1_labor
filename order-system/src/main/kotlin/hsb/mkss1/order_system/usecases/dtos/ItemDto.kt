package hsb.mkss1.order_system.usecases.dtos

import java.util.UUID

data class ItemDto(val id: UUID,
                   val name: String,
                   val price: Int,
                   val quantity: Int )
