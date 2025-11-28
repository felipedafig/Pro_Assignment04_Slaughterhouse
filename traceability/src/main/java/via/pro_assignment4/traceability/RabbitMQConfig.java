package via.pro_assignment4.traceability;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange animalEventsExchange() {
        return new FanoutExchange("animal-events");
    }

    @Bean
    public FanoutExchange partEventsExchange() {
        return new FanoutExchange("part-events");
    }

    @Bean
    public FanoutExchange productEventsExchange() {
        return new FanoutExchange("product-events");
    }

    @Bean
    public Queue animalRegisteredQueue() {
        return new Queue("trace-animal-queue", false);
    }

    @Bean
    public Queue partsQueue() {
        return new Queue("trace-part-queue", false);
    }

    @Bean
    public Queue productQueue() {
        return new Queue("trace-product-queue", false);
    }

    @Bean
    public Binding bindingAnimalTrace(Queue animalRegisteredQueue, FanoutExchange animalEventsExchange) {
        return BindingBuilder.bind(animalRegisteredQueue).to(animalEventsExchange);
    }

    @Bean
    public Binding bindingPartTrace(Queue partsQueue, FanoutExchange partEventsExchange) {
        return BindingBuilder.bind(partsQueue).to(partEventsExchange);
    }

    @Bean
    public Binding bindingProductTrace(Queue productQueue, FanoutExchange productEventsExchange) {
        return BindingBuilder.bind(productQueue).to(productEventsExchange);
    }
}
