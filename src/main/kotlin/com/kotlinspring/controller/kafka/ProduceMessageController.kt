package com.kotlinspring.controller.kafka

import com.kotlinspring.entities.PaymentVO
import com.kotlinspring.gateways.kafka.producer.PaymentKafkaProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProduceMessageController {

    @Autowired
    lateinit var paymentKafkaProducer: PaymentKafkaProducer

    @PostMapping("/produce")
    fun produceMessage(@RequestBody payment : PaymentVO) : ResponseEntity<String> {

        paymentKafkaProducer.produceMessage(payment)

        return ResponseEntity("status: message produced", HttpStatus.OK)

    }

}