package via.pro_assignment4;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public FanoutExchange animalEventsExchange() {
        return new FanoutExchange("animal-events");
    }
}
