package com.kotlinspring.drivers.kafka.consumer

import com.kotlinspring.drivers.kafka.producer.schemaRegistryUrlKey
import io.confluent.examples.clients.basicavro.Payment
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import java.util.*

@EnableKafka
@Configuration
class KafkaConsumerConfig {

    @Value("\${kafka.bootstrap.servers}")
    lateinit var bootstrapServer: String

    @Value("\${schema.registry.url}")
    lateinit var schemaRegistryUrlValue: String

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Payment> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServer
        props[ConsumerConfig.GROUP_ID_CONFIG] = "groupId"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaAvroDeserializer::class.java
        props[schemaRegistryUrlKey] = schemaRegistryUrlValue
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Payment> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Payment>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

}