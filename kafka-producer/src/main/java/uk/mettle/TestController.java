package uk.mettle;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("test")
public class TestController {

    @Inject
    ExampleProducer producer;

    @Post
    public String add(@Body String message) {
        producer.sendMessage(message);
        return message;
    }

}
