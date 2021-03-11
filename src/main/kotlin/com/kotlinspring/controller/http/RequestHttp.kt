package com.kotlinspring.controller.http

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestHttp(
    @JsonProperty("userName")
    val userName : String

    )
