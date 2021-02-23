package com.kotlinspring.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_apps")
data class UserEntity(

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id : Int?,

    @Column(name = "userName")
    val userName : String

)
