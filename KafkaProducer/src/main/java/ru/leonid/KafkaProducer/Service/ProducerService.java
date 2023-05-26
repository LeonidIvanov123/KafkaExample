package ru.leonid.KafkaProducer.Service;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class ProducerService {

    @Autowired
    private Producer<String,String> kafkaProducer;

    @Autowired
    NewTopic topic2;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Scheduled(initialDelay = 10000L, fixedDelay = 3000L)
    private void messageSender() throws ExecutionException, InterruptedException {
        System.out.println("Сейчас отправим:...");
        String msg = "privet from sender at " + (new Date().toString());
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, null, msg);

        RecordMetadata recordMetadata =
        kafkaProducer.send(producerRecord).get(); //ждем ответа после отправки в топик
        System.out.println("send message: " + msg + "\n offset = "+ recordMetadata.offset() +
                            " topic = " + recordMetadata.topic() + " timestamp = " +
                            recordMetadata.timestamp() + "\n");

        //kafkaProducer.send(producerRecord);
        //System.out.println("send message " + msg);


    }

}
