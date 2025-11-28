package via.station2;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import via.common.AnimalRegisteredEvent;

@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange partEventsExchange() {
        return new FanoutExchange("part-events");
    }

    @Bean
    public FanoutExchange animalEventsExchange() {
        return new FanoutExchange("animal-events");
    }

    @Bean
    public Queue animalQueue() {
        return new Queue("st2-animal-queue", false);
    }

    @Bean
    public Binding bindingAnimal(Queue animalQueue, FanoutExchange animalEventsExchange) {
        return BindingBuilder.bind(animalQueue).to(animalEventsExchange);
    }

    @RabbitListener(queues = "st2-animal-queue")
    public void receiveAnimalRegistered(AnimalRegisteredEvent event) {
        System.out.println("Station 2 received Animal Registered: " + event.getRegistrationNumber());
    }
}
