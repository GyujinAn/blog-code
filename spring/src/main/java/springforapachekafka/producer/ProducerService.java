package springforapachekafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerService {

    private static final String TOPIC = "sample";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message){
        log.info("sendMessage is started; message: " + message);
        kafkaTemplate.send(TOPIC, message);
    }
}
