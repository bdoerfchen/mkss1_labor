package hsb.mkss1.order_system.controller.errors;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

@Slf4j
@Component("rabbitMQErrorHandler")
public class RabbitErrorHandler implements RabbitListenerErrorHandler {

    @Override
    public @Nullable Object handleError(Message amqpMessage, @Nullable Channel channel, org.springframework.messaging.@Nullable Message<?> message, ListenerExecutionFailedException exception) throws Exception {
        log.error(exception.getMessage());
        return null;
    }
}


