package hsb.mkss1.order_system.controller

import hsb.mkss1.order_system.usecases.OrderHandler
import hsb.mkss1.order_system.usecases.dtos.InitializeOrderTemplate
import hsb.mkss1.order_system.usecases.dtos.ItemDto
import hsb.mkss1.order_system.usecases.dtos.ItemTemplate
import hsb.mkss1.order_system.usecases.dtos.OrderDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*


@RestController
@RequestMapping("/orders")
class OrderController(val orderHandler: OrderHandler) {




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
        return orderHandler.addItemToOrder(orderId,template)
    }


    @DeleteMapping(value = ["/{orderId}/items/{itemId}"])
    fun addItemToOrder(@PathVariable orderId : UUID, @PathVariable itemId : UUID) {
        return orderHandler.removeItemFromOrder(orderId,itemId)
    }

    @DeleteMapping(value = ["/{orderId}"])
    fun addItemToOrder(@PathVariable orderId : UUID) {
        return orderHandler.deleteOrder(orderId)
    }


    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["/{orderId}"])
    fun getById(@PathVariable orderId: UUID): OrderDto {
        try {
            return orderHandler.getById(orderId)
        }
        catch (e: NoSuchElementException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "not found"
            )
        }
    }

}
