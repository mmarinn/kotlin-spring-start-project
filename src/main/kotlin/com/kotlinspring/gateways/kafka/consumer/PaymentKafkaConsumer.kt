package com.kotlinspring.gateways.kafka.consumer

import io.confluent.examples.clients.basicavro.Payment
import org.apache.avro.generic.GenericRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PaymentKafkaConsumer {

    @KafkaListener(topics = ["payment-topic"], groupId = "foo")
    fun listenGroupFoo(genericRecord: GenericRecord) {
        val payment = Payment.newBuilder().let {
            it.id = genericRecord["id"] as String
            it.amount = genericRecord["amount"] as Double
            it.build()
        }

        println("Received Message with id: ${payment.getId()}")
    }
}