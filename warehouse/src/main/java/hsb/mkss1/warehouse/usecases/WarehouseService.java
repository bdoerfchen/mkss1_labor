package hsb.mkss1.warehouse.usecases;

import de.hsbremen.mkss.events.CrudEventProducer;
import de.hsbremen.mkss.events.EventWithPayload;
import hsb.mkss1.warehouse.usecases.dtos.OrderEventDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WarehouseService {

    private final CrudEventProducer<OrderEventDto> eventProducer;

    // Incoming events from the OrderSystem
    @RabbitListener(queues="${rabbitmq.queue.ordersystem}")
    public void receiveMessage(EventWithPayload<OrderEventDto> event) {
        // Determine if acceped or rejected
        event.set
         if (Math.random() > 0.5f) {

        }

        // Send change event
    }
}
