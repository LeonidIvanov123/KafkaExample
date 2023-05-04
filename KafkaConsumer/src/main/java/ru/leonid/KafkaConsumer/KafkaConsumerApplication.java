package ru.leonid.KafkaConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableKafka
public class KafkaConsumerApplication {

	public static void main(String[] args) {

		//System.getProperties().put("server.port", 8085);

		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
