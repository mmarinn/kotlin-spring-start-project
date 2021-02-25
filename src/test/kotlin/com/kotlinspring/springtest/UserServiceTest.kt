package com.kotlinspring.springtest

import com.kotlinspring.drivers.database.UserRepository
import com.kotlinspring.services.UserService
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.spring.SpringListener
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration


@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = [UserServiceTestConfiguration::class])
class UserServiceTest : BehaviorSpec() {

    override fun listeners(): List<TestListener> {
        return listOf(SpringListener)
    }

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userRepository: UserRepository


    init {
        given("a new userName") {
            val username = "newUserName"
            `when`("create this new user in database") {
                userService.createNewUser(username)
                then("should persist in database") {
                    verify(exactly = 1) { userRepository.save(any()) }
                }
            }
        }
    }

}