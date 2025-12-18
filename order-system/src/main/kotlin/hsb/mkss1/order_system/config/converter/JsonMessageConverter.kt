package hsb.mkss1.order_system.config.converter

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.support.converter.MessageConverter
import tools.jackson.databind.ObjectMapper

class JsonMessageConverter (
    val objectMapper: ObjectMapper = ObjectMapper()
) : MessageConverter {
    override fun toMessage(
        `object`: Any,
        messageProperties: MessageProperties
    ): Message {
        val bytes = objectMapper.writeValueAsBytes(`object`)
        messageProperties.contentType = MessageProperties.CONTENT_TYPE_JSON
        return Message(bytes, messageProperties)
    }

    override fun fromMessage(message: Message): Any {
        return objectMapper.readValue(message.body, Any::class.java)
    }
}