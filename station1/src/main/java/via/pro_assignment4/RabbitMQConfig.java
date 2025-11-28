package via.pro_assignment4;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue animalRegisteredQueue() {
        return new Queue("animal.registered", false);
    }
}
