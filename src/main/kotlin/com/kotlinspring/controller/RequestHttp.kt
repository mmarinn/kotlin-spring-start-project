package com.kotlinspring.controller

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestHttp(
    @JsonProperty("userName")
    val userName : String

    )
