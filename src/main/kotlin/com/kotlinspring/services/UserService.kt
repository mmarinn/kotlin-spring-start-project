package com.kotlinspring.services

import com.kotlinspring.drivers.database.UserRepository
import com.kotlinspring.entities.UserEntity
import java.util.*

import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    @Throws(RuntimeException::class)
    fun createNewUser(userNameToCreate: String) {

        if (userExists(userNameToCreate).isPresent) {
            throw RuntimeException("User already created")
        }

        val userEntity = UserEntity(null, userNameToCreate)
        userRepository.save(userEntity)
    }

    @Throws(RuntimeException::class)
    fun userExists(userName: String): Optional<UserEntity> {
        return userRepository.findByUserName(userName)
    }



}