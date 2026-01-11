package hsb.mkss1.order_system.controller

import hsb.mkss1.order_system.controller.errors.ErrorResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/status")
@Tag(name = "Status", description = "System status information")
class StatusController {

    /**
     * Endpoint to check the status of the Order System.
     * @return A plain text message indicating the system is running.
     */
    @GetMapping(produces = [MediaType.TEXT_PLAIN_VALUE])
    @Operation(summary = "Get system Status", description = "Endpoint to check the status of the Order System.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "System is up"),
    ])
    fun getStatus(): String = "Order System is running"

}
