package hsb.mkss1.warehouse.adapter.events;

import de.hsbremen.mkss.events.CrudEventProducer;
import de.hsbremen.mkss.events.Event;
import de.hsbremen.mkss.events.EventWithPayload;
import de.hsbremen.mkss.shared.dtos.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventsProducer implements CrudEventProducer<OrderDto> {

	private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.warehouse}")
    private String warehouseExchange;

    @Value("${rabbitmq.routingkey.warehouse}")
    private String warehouseRoutingKey;


	public OrderEventsProducer(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}


	private EventWithPayload<OrderDto> buildEvent(Event.EventType type, OrderDto payload) {
		return EventWithPayload.<OrderDto> builder()
				.type(type)
				.payload(payload)
				.build();
	}

	@Override
	public void emitCreateEvent(OrderDto payload) {
        emitEventWithType(Event.EventType.CREATED, payload);
	}

	@Override
	public void emitUpdateEvent(OrderDto payload) {
        emitEventWithType(Event.EventType.CHANGED, payload);
	}

	@Override
	public void emitDeleteEvent(OrderDto payload) {
        emitEventWithType(Event.EventType.DELETED, payload);
	}

    private void emitEventWithType(Event.EventType type, OrderDto payload) {
        EventWithPayload<OrderDto> event = buildEvent(type, payload);
        amqpTemplate.convertAndSend(warehouseExchange, warehouseRoutingKey, event);
        log.info("Sent event = {} using exchange {} with routing key {}", event, warehouseExchange, warehouseRoutingKey);
    }
}
