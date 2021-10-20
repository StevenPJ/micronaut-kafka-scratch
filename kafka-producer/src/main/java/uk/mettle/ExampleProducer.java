package uk.mettle;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface ExampleProducer {

    @Topic("example")
    void sendMessage(String message);
}
