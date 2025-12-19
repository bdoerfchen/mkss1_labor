package hsb.mkss1.order_system.controller

import de.hsbremen.mkss.events.Event
import de.hsbremen.mkss.events.EventWithPayload
import de.hsbremen.mkss.shared.dtos.*
import hsb.mkss1.order_system.usecases.OrderHandler
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service


@Service
@AllArgsConstructor
class RabbitMQOrderController(val orderHandler: OrderHandler) {

    val log: Logger = LoggerFactory.getLogger(RabbitMQOrderController::class.java)

    @RabbitListener(queues = ["\${rabbitmq.queue.warehouse}"], errorHandler = "rabbitMQErrorHandler")
    fun receiveMessage(@Payload event: EventWithPayload<OrderDto>) {
        // Only handle change events
        if (event.type != Event.EventType.CHANGED) {
            return
        }
        log.info("Received change event: {}", event);

        val order: OrderDto = event.payload
        when (order.status) {
            OrderStatusEnumDto.ACCEPTED -> {
                orderHandler.acceptOrder(order.id);
                log.info("Used warehouse decision to accept order {}", order.id);
            }
            OrderStatusEnumDto.REJECTED -> {
                orderHandler.rejectOrder(order.id);
                log.info("Used warehouse decision to reject order {}", order.id);
            }
            else -> {
                error("Listener does not accept this state change")
            }
        }
    }

}
