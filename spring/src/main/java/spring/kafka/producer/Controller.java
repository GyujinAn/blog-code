package spring.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class Controller {

    private final ProducerService producerService;

    @PostMapping
    public String sendMessage(@RequestBody MessageDto message){
        producerService.sendMessage(message.getMessage());
        return "OK";
    }


}
