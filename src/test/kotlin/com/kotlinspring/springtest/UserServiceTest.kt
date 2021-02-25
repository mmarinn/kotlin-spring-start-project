package com.kotlinspring.springtest

import com.kotlinspring.drivers.database.UserRepository
import com.kotlinspring.services.UserService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.test.TestCaseOrder
import io.kotest.matchers.shouldBe
import io.kotest.spring.SpringListener
import kotlin.time.ExperimentalTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration


@ExperimentalTime
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = [UserServiceTestConfiguration::class])
class UserServiceTest : BehaviorSpec() {

    override fun listeners(): List<TestListener> {
        return listOf(SpringListener)
    }

    override fun testCaseOrder(): TestCaseOrder? = TestCaseOrder.Sequential

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
                    userService.userExists(username).isPresent shouldBe true

                }
            }
        }

        given("a userName already saved") {
            val userNameSaved = "userNameSaved"
            userService.createNewUser(userNameSaved)
            `when`("create this new user in database") {
                shouldThrow<RuntimeException> {
                    userService.createNewUser(userNameSaved)
                }.message shouldBe "User already created"
                then("should not persist in database") {
                    userService.userExists(userNameSaved).isPresent shouldBe true
                }
            }
        }

        given("a user that exists in database") {
            val usernameSaved = "newUserNameSavedTwo"
            userService.createNewUser(usernameSaved)
            `when`("i check with this exists") {
                val optionalUser = userService.userExists(usernameSaved)
                then("i should found it") {
                    optionalUser.isPresent shouldBe true
                }
            }
        }

        given("a user that not exists in database") {
            val usernameSaved = "newUserNameSavedThree"
            `when`("i check with this exists") {
                val optionalUser = userService.userExists(usernameSaved)
                then("i should not found it") {
                    optionalUser.isPresent shouldBe false
                }
            }
        }


    }

}