package hsb.mkss1.warehouse.controller;

import de.hsbremen.mkss.events.CrudEventProducer;
import de.hsbremen.mkss.events.EventWithPayload;
import de.hsbremen.mkss.shared.dtos.OrderDto;
import de.hsbremen.mkss.shared.dtos.OrderStatusEnumDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class WarehouseController {

    private final CrudEventProducer<OrderDto> eventProducer;

    // Incoming events from the OrderSystem
    @RabbitListener(queues="${rabbitmq.queue.ordersystem}", messageConverter = "eventMessageConverter")
    public void receiveMessage(@Payload EventWithPayload<OrderDto> event) {
        log.info("Received event: {}", event);

        // Determine if accepted or rejected
        var newStatus = Math.random() > 0.5f ? OrderStatusEnumDto.ACCEPTED : OrderStatusEnumDto.REJECTED;

        // Update order
        var order = event.getPayload();
        var changedOrder = new OrderDto(
                order.getId(),
                order.getItems(),
                order.getLumpSum(),
                order.getCheckoutTimestamp(),
                newStatus,
                order.getCustomerName()
        );

        // Send update event
        log.info("Warehouse decision for order {} is {}", order.getId(), newStatus);
        eventProducer.emitUpdateEvent(changedOrder);
    }
}
