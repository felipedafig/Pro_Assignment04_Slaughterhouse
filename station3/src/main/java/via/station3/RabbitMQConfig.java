package via.station3;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import via.common.PartAddedToTrayEvent;
import via.station3.Model.TrayAnimalLookup;
import via.station3.Repositories.TrayAnimalLookupRepository;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private TrayAnimalLookupRepository trayAnimalLookupRepository;

    @Bean
    public FanoutExchange partEventsExchange() {
        return new FanoutExchange("part-events");
    }

    @Bean
    public FanoutExchange productEventsExchange() {
        return new FanoutExchange("product-events");
    }

    @Bean
    public Queue partQueue() {
        return new Queue("st3-part-queue", false);
    }

    @Bean
    public Binding bindingPart(Queue partQueue, FanoutExchange partEventsExchange) {
        return BindingBuilder.bind(partQueue).to(partEventsExchange);
    }

    @RabbitListener(queues = "st3-part-queue")
    public void receivePartAddedToTray(PartAddedToTrayEvent event) {
        System.out.println("Station 3 received Part Added to Tray: " + event.getTrayId() + ", Animal: " + event.getAnimalRegistrationNumber());


        TrayAnimalLookup lookup = new TrayAnimalLookup(event.getTrayId(), event.getAnimalRegistrationNumber());
        trayAnimalLookupRepository.save(lookup);
    }
}
