package springforapachekafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {

    @KafkaListener(topics = "sample", groupId = "foo")
    public void consume(String message){
        log.info("consume is started; message: " + message);
    }

}
