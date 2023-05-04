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
                bootstrapServers);
        kaProperties.put("acks", "all"); //макс. надежность. Ждем подтверждения записи всех копий
        kaProperties.put("retries", "3"); // 3 попытки повторной отправки при сбое
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

    /*
	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;

	//KafkaAdminотвечает за создание новых тем в нашем брокере. В spring boot создается автоматически
	@Bean
	public KafkaAdmin kafkaAdmin(){
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new KafkaAdmin(configs);
	}
	@Bean
	public NewTopic topic2(){
		return new NewTopic("SPRING.TEST.TOPIC2", 1,(short) 1);
	}
	*/

}
