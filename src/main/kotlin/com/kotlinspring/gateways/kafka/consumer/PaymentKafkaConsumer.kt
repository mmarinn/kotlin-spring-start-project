package com.kotlinspring.gateways.kafka.consumer

import io.confluent.examples.clients.basicavro.Payment
import org.apache.avro.generic.GenericRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PaymentKafkaConsumer {

    @Value("\${topic.payment.name}")
    lateinit var paymentTopicName : String

    @KafkaListener(topics = ["paymentTopicName"], groupId = "foo")
    fun onMessage(genericRecord: GenericRecord) {
        val payment = Payment.newBuilder().let {
            it.id = genericRecord["id"] as String
            it.amount = genericRecord["amount"] as Double
            it.build()
        }

        println("Received Message with id: ${payment.getId()}")
    }
}