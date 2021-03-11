package com.kotlinspring.gateways.kafka.producer

import com.kotlinspring.entities.PaymentVO
import io.confluent.examples.clients.basicavro.Payment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PaymentKafkaProducer {

    @Autowired
    lateinit var  kafkaTemplate: KafkaTemplate<String, Payment>

    fun produceMessage(paymentVO: PaymentVO) {
        val payment = Payment.newBuilder().let {
            it.amount = paymentVO.amount
            it.id = paymentVO.id
            it.build()
        }

        kafkaTemplate.send("payment-topic", payment)
    }
}