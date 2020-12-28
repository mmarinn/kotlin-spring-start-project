package com.kotlinspring

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/teste")
    fun getById(): ResponseEntity<String> {
        return ResponseEntity("funfa desgraaaaca", HttpStatus.OK)
    }
}