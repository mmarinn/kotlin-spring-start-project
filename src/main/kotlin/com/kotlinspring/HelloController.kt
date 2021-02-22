package com.kotlinspring

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/helloworld")
    fun helloWorld(): ResponseEntity<ResponseHttp> {
        return ResponseEntity(ResponseHttp("Hello, user!"), HttpStatus.OK)
    }

    @GetMapping("/hello")
    fun helloUser(@RequestParam userName: String): ResponseEntity<ResponseHttp> {
        if (userName.isBlank()) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(ResponseHttp("Hello, $userName!"), HttpStatus.OK)
    }
}