package com.kotlinspring.drivers.kafka.producer

import io.confluent.examples.clients.basicavro.Payment
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig {

      @Bean
    fun producerFactory(): ProducerFactory<String, Payment> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "http://localhost:9092"
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "io.confluent.kafka.serializers.KafkaAvroSerializer"
        configProps["schema.registry.url"] = "http://localhost:8081"

        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Payment> {
        return KafkaTemplate(producerFactory())
    }

}