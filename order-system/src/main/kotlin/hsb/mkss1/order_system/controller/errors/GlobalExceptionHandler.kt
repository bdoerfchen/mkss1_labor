package hsb.mkss1.order_system.controller.errors

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.server.ResponseStatusException
import java.time.Instant

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatus(ex: ResponseStatusException, request: ServletWebRequest): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(
            timestamp = Instant.now().toString(),
            status = ex.statusCode.value(),
            error = ex.statusCode.toString(),
            reason = ex.reason,
            path = request.request.requestURI
        )
        return ResponseEntity.status(ex.statusCode).body(body)
    }
}