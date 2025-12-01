package hsb.mkss1.order_system.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/status")
class StatusController {

    /**
     * Endpoint to check the status of the Order System.
     * @return A plain text message indicating the system is running.
     */
    @GetMapping(produces = [MediaType.TEXT_PLAIN_VALUE])
    fun getStatus(): String = "Order System is running"

}
