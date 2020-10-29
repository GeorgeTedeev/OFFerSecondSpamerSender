package com.project.offer.secondspamersender.configs;



import com.project.offer.secondspamersender.entities.SpamTaskWithEmailAndNumberOfSpamerSender;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, SpamTaskWithEmailAndNumberOfSpamerSender> kafkaConsumerFactoryForSpamTask() {
        JsonDeserializer<SpamTaskWithEmailAndNumberOfSpamerSender> deserializer = new JsonDeserializer<>(SpamTaskWithEmailAndNumberOfSpamerSender.class, false);
        deserializer.addTrustedPackages("*");
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SpamTaskWithEmailAndNumberOfSpamerSender> kafkaListenerContainerFactoryForSpamTask(){
        ConcurrentKafkaListenerContainerFactory<String, SpamTaskWithEmailAndNumberOfSpamerSender> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactoryForSpamTask());
        return factory;
    }

}
