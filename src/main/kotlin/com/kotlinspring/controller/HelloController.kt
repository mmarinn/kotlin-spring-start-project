package com.kotlinspring.controller

import com.kotlinspring.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    private val logger = LoggerFactory.getLogger(HelloController::class.java)

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/helloworld")
    fun helloWorld(): ResponseEntity<ResponseHttp> {
        return ResponseEntity(ResponseHttp("Hello, user!"), HttpStatus.OK)
    }

    @GetMapping("/hello")
    fun helloUser(@RequestParam userName: String): ResponseEntity<ResponseHttp> {
        if (userName.isBlank()) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(ResponseHttp("Hello, ${userName}!"), HttpStatus.OK)
    }

    @PostMapping("/user")
    fun createNewUser(@RequestBody request: RequestHttp): ResponseEntity<HttpStatus> {
        try {
            userService.createNewUser(request.userName)

        } catch (e: Throwable) {
            if (e.message?.contains("User already created") == true) {
                logger.info("User with userName ${request.userName} already created")
                return ResponseEntity(HttpStatus.CONFLICT)
            }

            logger.info("Error, we cannot create your user")
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        logger.info("User with userName ${request.userName} created")
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/user")
    fun helloFromSpecificUser(@RequestParam userName: String): ResponseEntity<ResponseHttp> {
        try {
            val userOptional = userService.userExists(userName)

            if (userOptional.isEmpty) {
                logger.info("User with email $userName not found")
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }

            return ResponseEntity(ResponseHttp("Hello, ${userName}!"), HttpStatus.OK)
        } catch (e: Throwable) {
            logger.error("Error searching username $userName" + e.message)
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}