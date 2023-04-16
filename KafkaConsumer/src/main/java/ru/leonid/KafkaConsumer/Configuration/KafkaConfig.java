package ru.leonid.KafkaConsumer.Configuration;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private KafkaConsumer<String, String> consumer;

    private void config(){
        Properties kaProperties = new Properties();
        kaProperties.put("bootstrap.servers", bootstrapServers);
       // kaProperties.put("group.id", "kinaction_helloconsumer");
        //kaProperties.put("enable.auto.commit", "true");
        //kaProperties.put("auto.commit.interval.ms", "1000");
        kaProperties.put("group.id", "r4zt_wrqTRuT7W2NJsB_GA");
        kaProperties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        kaProperties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(kaProperties);
    }

    @Bean
    public KafkaConsumer<String,String> kafkaConsumer(){
        config();
        return consumer;
    }



}
