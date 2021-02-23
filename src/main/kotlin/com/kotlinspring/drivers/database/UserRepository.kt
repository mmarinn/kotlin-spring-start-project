package com.kotlinspring.drivers.database

import com.kotlinspring.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findByUserName(userName : String) : Optional<UserEntity>
}