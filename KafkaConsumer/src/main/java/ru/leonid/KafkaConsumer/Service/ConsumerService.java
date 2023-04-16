package ru.leonid.KafkaConsumer.Service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private KafkaConsumer<String,String> kafkaConsumer;

    @Value("${spring.kafka.template.default-topic}")
    String topic;

    @Scheduled(initialDelay = 5000L, fixedDelay = 20000L)
    private void consumeTo(){

        kafkaConsumer.subscribe(List.of(topic));

        while (true) {
            ConsumerRecords<String, String> records =
                    kafkaConsumer.poll(Duration.ofMillis(10000));

            for(ConsumerRecord<String, String> record: records){
                System.out.println("offset = "+ record.offset() +
                        " value = " + record.value());
            }
        }
    }
}
