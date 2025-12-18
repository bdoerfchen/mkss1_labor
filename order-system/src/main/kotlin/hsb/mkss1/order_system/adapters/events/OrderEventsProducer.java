package hsb.mkss1.order_system.adapters.events;

import de.hsbremen.mkss.events.CrudEventProducer;
import de.hsbremen.mkss.events.Event;
import de.hsbremen.mkss.events.EventWithPayload;
import hsb.mkss1.order_system.usecases.dtos.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventsProducer implements CrudEventProducer<OrderDto> {

	private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.ordersystem}")
    private String orderSystemExchange;

    @Value("${rabbitmq.routingkey.ordersystem}")
    private String orderSystemRoutingKey;


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
        EventWithPayload<OrderDto> event = new EventWithPayload<>(type, payload);
        amqpTemplate.convertAndSend(orderSystemExchange, orderSystemRoutingKey, event);
        log.info("Sent event = {} using exchange {} with routing key {}", event, orderSystemExchange, orderSystemRoutingKey);
    }
}
