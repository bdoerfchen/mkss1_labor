package hsb.mkss1.order_system.config;

import hsb.mkss1.order_system.config.converter.JsonMessageConverter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Value("${rabbitmq.queue.warehouse}")
    String queueWarehouseName;

    @Value("${rabbitmq.routingkey.warehouse}")
    String routingKeyWarehouse;



    @Bean("orderSystemExchange")
    DirectExchange orderSystemExchange() {
        return new DirectExchange(exchangeNameOrderSystem);
    }

    @Bean("warehouseExchange")
    DirectExchange warehouseExchange() {
        return new DirectExchange(exchangeNameWarehouse);
    }

    @Bean("warehouseQueue")
    Queue warehouseQueue() {
        return new Queue(queueWarehouseName, false);
    }

    // Bindings are required for receiving event messages:
    // connecting of a queue to an exchange 
    @Bean
    Binding warehouseBinding(@Qualifier("warehouseQueue") Queue queue, @Qualifier("warehouseExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyWarehouse);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
