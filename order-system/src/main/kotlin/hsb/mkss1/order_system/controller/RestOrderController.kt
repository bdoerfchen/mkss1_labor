package hsb.mkss1.order_system.controller

import de.hsbremen.mkss.shared.dtos.*
import hsb.mkss1.order_system.usecases.OrderHandler
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*


@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Actions related to orders")
class RestOrderController(val orderHandler: OrderHandler) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "Get all orders", description = "Get a list of all orders")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "400", description = "Bad request")
    ])
    fun getAllOrders(): List<OrderDto> {
       return orderHandler.getAll()
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "Create order", description = "Create a new empty order")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success"),
    ])
    fun postOrder(template: InitializeOrderTemplate): OrderDto {
        return orderHandler.initializeOrder(template)
    }


    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["/{orderId}/items"])
    @Operation(summary = "Add item to order", description = "Create a new item and add it to the specified order")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Success"),
        ApiResponse(responseCode = "400", description = "Order cannot be modified"),
        ApiResponse(responseCode = "404", description = "Specified order not found"),
    ])
    fun addItemToOrder(
        @Schema(description = "Unique identifier of the order", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        @PathVariable orderId : UUID,
        template: ItemTemplate,
    ): ItemDto {
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
    @Operation(summary = "Delete an item", description = "Removes the item with the given id from the order")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully deleted the item"),
        ApiResponse(responseCode = "404", description = "No such item or order was found")
    ])
    fun deleteItemFromOrder(
        @Schema(description = "The id of the order the item is a part of",
                example = "3fa85f64-5717-4562-b3fc-2c963f66afa5")
        @PathVariable orderId : UUID,

        @Schema(description = "The id of the item that is to be deleted",
                example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        @PathVariable itemId : UUID) {
        try {
            return orderHandler.removeItemFromOrder(orderId,itemId)
        } catch (_: NoSuchElementException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such item or order was found"
            )
        }
    }

    @DeleteMapping(value = ["/{orderId}"])
    @Operation(summary = "Delete an order", description = "Removes an order and all it's items from the system")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully deleted the order"),
        ApiResponse(responseCode = "404", description = "No such order was found")
    ])
    fun deleteOrder(
        @Schema(description = "The id of the order that is to be deleted",
                example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        @PathVariable orderId : UUID) {
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
