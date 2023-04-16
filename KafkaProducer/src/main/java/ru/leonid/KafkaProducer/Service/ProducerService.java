package ru.leonid.KafkaProducer.Service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.leonid.KafkaProducer.Configuration.KafkaConfig;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Properties;

@Service
public class ProducerService {

    @Autowired
    private Producer<String,String> kafkaProducer;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Scheduled(initialDelay = 10000L, fixedDelay = 7000L)
    private void messageSender(){
        String msg = "privet from sender at " + (new Date().toString());
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, null, msg);
        kafkaProducer.send(producerRecord);
        System.out.println("send message: " + msg);
    }

}