package via.station2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import via.common.AnimalRegisteredEvent;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue partAddedToTrayQueue() {
        return new Queue("part.added.to.tray", false);
    }

    @RabbitListener(queues = "animal.registered")
    public void receiveAnimalRegistered(AnimalRegisteredEvent event) {
        System.out.println("Station 2 received Animal Registered: " + event.getRegistrationNumber());
        // Store locally if needed for validation
    }
}
