package uk.mettle;

import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Factory
public class ExampleFactory {

    @Singleton
    @Named("example")
    KStream<String, String> exampleStream(ConfiguredStreamBuilder builder) {
        var streamThing =  builder.stream("example", Consumed.with(Serdes.String(), Serdes.String()));
        streamThing.peek((k,v) -> System.out.println(v));
        return streamThing;
    }
}
