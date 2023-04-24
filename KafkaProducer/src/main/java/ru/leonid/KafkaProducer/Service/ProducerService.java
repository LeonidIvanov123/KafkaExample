package ru.leonid.KafkaProducer.Service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.leonid.KafkaProducer.Configuration.KafkaConfig;

import javax.xml.crypto.Data;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class ProducerService {

    @Autowired
    private Producer<String,String> kafkaProducer;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Scheduled(initialDelay = 10000L, fixedDelay = 7000L)
    private void messageSender() throws ExecutionException, InterruptedException {
        String msg = "privet from sender at " + (new Date().toString());
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, null, msg);
        RecordMetadata recordMetadata =
        kafkaProducer.send(producerRecord).get(); //ждем ответа после отправки в топик

        System.out.println("send message: " + msg + "\n offset = "+ recordMetadata.offset() + " topic = " + recordMetadata.topic() + " timestamp = " + recordMetadata.timestamp() + "\n");
    }

}
