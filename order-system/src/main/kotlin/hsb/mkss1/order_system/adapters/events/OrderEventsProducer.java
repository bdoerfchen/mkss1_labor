package hsb.mkss1.order_system.adapters.events;

import de.hsbremen.mkss.events.CrudEventProducer;
import de.hsbremen.mkss.events.Event;
import de.hsbremen.mkss.events.EventWithPayload;
import hsb.mkss1.order_system.usecases.dtos.OrderDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsProducer implements CrudEventProducer<OrderDto> {

	private AmqpTemplate amqpTemplate;

    @Value("${my.rabbitmq.an.exchange}")
    String anExchangeName;

    @Value("${my.rabbitmq.a.routing.key}")
    String aRoutingKeyName;


	public OrderEventsProducer(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}


	private EventWithPayload<OrderDto> buildEvent(Event.EventType type, OrderDto payload) {
		EventWithPayload<OrderDto> event = EventWithPayload.<OrderDto> builder()
				.type(type)
				.payload(payload)
				.build();
		return event;
	}

	@Override
	public void emitCreateEvent(OrderDto payload) {
		// TODO: Implementation for create events (e.g. new order)
		EventWithPayload<OrderDto> event = new EventWithPayload<>(Event.EventType.CREATED, payload);

		// TODO: send event to RabbitMQ exchange


		System.out.println("Sent event = " + event + " using exchange " + anExchangeName + " with routing key " + aRoutingKeyName);
	}

	@Override
	public void emitUpdateEvent(OrderDto payload) {
		// TODO: Implementation for update events (e.g. changed order)
	}

	@Override
	public void emitDeleteEvent(OrderDto payload) {
		// TODO: Implementation for delete events (e.g. deleted order)
	}
}
