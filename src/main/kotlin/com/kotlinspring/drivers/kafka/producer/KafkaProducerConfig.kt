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

    @Value("\${kafka.bootstrap.servers}")
    lateinit var bootstrapServer: String

    @Value("\${kafka.key.serializer}")
    lateinit var keySerializer: String

    @Value("\${kafka.value.serializer}")
    lateinit var valueSerializer: String

    @Value("\${schema.registry.url}")
    lateinit var schemaRegistryUrlValue: String

    @Bean
    fun producerFactory(): ProducerFactory<String, Payment> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = keySerializer
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = valueSerializer
        configProps[schemaRegistryUrlKey] = schemaRegistryUrlValue

        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Payment> {
        return KafkaTemplate(producerFactory())
    }
}

const val schemaRegistryUrlKey = "schema.registry.url"