package com.kotlinspring.styles

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldContain

class StringSpecSimpleTest : StringSpec() {
    init {
        "the function should throws a RunTimeException with error message ooops" {
            shouldThrow<RuntimeException> {
                functionThatThrowsException()
            }.message shouldContain "ooops"
        }
    }

    fun functionThatThrowsException() {
        throw RuntimeException("ooops")
    }
}