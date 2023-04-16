package ru.leonid.KafkaProducer.Configuration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private Producer<String, String> producer;

    private void configKafka() {
        Properties kaProperties = new Properties();
        kaProperties.put("bootstrap.servers",
                "localhost:9092,localhost:9093,localhost:9094");
        kaProperties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kaProperties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(kaProperties);
    }

    @Bean
    public Producer<String, String> kafkaProducer() {
        configKafka();
        return producer;
    }
}
