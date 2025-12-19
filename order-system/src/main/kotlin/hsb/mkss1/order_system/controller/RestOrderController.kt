package hsb.mkss1.order_system.controller

import de.hsbremen.mkss.events.EventWithPayload
import de.hsbremen.mkss.shared.dtos.*
import hsb.mkss1.order_system.usecases.OrderHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*


@RestController
@RequestMapping("/orders")
class RestOrderController(val orderHandler: OrderHandler) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllOrders(): List<OrderDto> {
       return orderHandler.getAll()
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postOrder(template: InitializeOrderTemplate): OrderDto {
        return orderHandler.initializeOrder(template)
    }


    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["/{orderId}/items"])
    fun addItemToOrder(@PathVariable orderId : UUID, template: ItemTemplate): ItemDto {
        try {
            return orderHandler.addItemToOrder(orderId,template)
        } catch (_: NoSuchElementException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "No order with id $orderId found"
            )
        } catch (_: IllegalStateException) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Order with id $orderId cannot be modified"
            )
        }
    }


    @DeleteMapping(value = ["/{orderId}/items/{itemId}"])
    fun addItemToOrder(@PathVariable orderId : UUID, @PathVariable itemId : UUID) {
        try {
            return orderHandler.removeItemFromOrder(orderId,itemId)
        } catch (_: NoSuchElementException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "No order with id $orderId found"
            )
        }
    }

    @DeleteMapping(value = ["/{orderId}"])
    fun addItemToOrder(@PathVariable orderId : UUID) {
        try {
            return orderHandler.deleteOrder(orderId)
        } catch (_: NoSuchElementException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "No order with id $orderId found"
            )
        }
    }

    @PutMapping(value = ["/{orderId}/purchases"])
    fun commitPurchase(@PathVariable orderId : UUID): OrderDto? {
        try {
            return orderHandler.finalizeOrder(orderId)
        } catch (_: NoSuchElementException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "No order with id $orderId found"
            )
        } catch (_: IllegalStateException) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Order with id $orderId cannot be finalized"
            )
        }
    }


    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["/{orderId}"])
    fun getById(@PathVariable orderId: UUID): OrderDto {
        try {
            return orderHandler.getById(orderId)
        } catch (_: NoSuchElementException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "No order with id $orderId found"
            )
        }
    }

}
