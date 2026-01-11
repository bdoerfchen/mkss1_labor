package hsb.mkss1.order_system.controller.errors

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    name = "Error Response",
    description = "Representation of an error"
)
data class ErrorResponse(
    @Schema(description = "Timestamp of the error", example = "2026-01-11T11:54:50.867Z")
    val timestamp: String,

    @Schema(description = "HTTP status code", example = "404")
    val status: Int,

    @Schema(description = "HTTP error message", example = "Not Found")
    val error: String,

    @Schema(description = "Detailed error message", example = "Can not find item by id")
    val reason: String?,

    @Schema(description = "API controller path", example = "/test")
    val path: String
)