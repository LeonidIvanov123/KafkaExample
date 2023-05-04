package ru.leonid.KafkaConsumer.Configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.UUID;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private KafkaConsumer<String, String> consumer;

    private void config(){
        Properties kaProperties = new Properties();
        kaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        //kaProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //kaProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        kaProperties.put(ConsumerConfig.GROUP_ID_CONFIG , "myConsumerGroup");//"r4zt_wrqTRuT7W2NJsB_GA");
        kaProperties.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG,("SchedulerCoordinator"+ UUID.randomUUID()));
        kaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        kaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(kaProperties);
    }

    @Bean
    public KafkaConsumer<String,String> kafkaConsumer(){
        config();
        return consumer;
    }



}
