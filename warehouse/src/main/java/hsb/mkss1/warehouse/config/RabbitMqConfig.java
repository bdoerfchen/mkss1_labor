package hsb.mkss1.warehouse.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.exchange.ordersystem}")
    String exchangeNameOrderSystem;

    @Value("${rabbitmq.exchange.warehouse}")
    String exchangeNameWarehouse;

    @Value("${rabbitmq.queue.ordersystem}")
    String queueOrderSystemName;

    @Value("${rabbitmq.routingkey.ordersystem}")
    String routingKeyOrderSystem;



    @Bean("orderSystemExchange")
    DirectExchange orderSystemExchange() {
        return new DirectExchange(exchangeNameOrderSystem);
    }

    @Bean("warehouseExchange")
    DirectExchange warehouseExchange() {
        return new DirectExchange(exchangeNameWarehouse);
    }

    @Bean("orderSystemQueue")
    Queue orderSystemQueue() {
        return new Queue(queueOrderSystemName, false);
    }

    // Bindings are required for receiving event messages:
    // connecting of a queue to an exchange 
    @Bean
    Binding warehouseBinding(@Qualifier("orderSystemQueue") Queue queue, @Qualifier("orderSystemExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyOrderSystem);
    }


    @Bean("eventMessageConverter")
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
