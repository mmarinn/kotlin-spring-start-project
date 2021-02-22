package com.kotlinspring

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class SimpleTestExample : BehaviorSpec() {
    init {
        given("a simple string foo") {
            val foo = "foo"
            `when`("the value is foo") {
                foo.shouldContain("foo")
                then("a equals e") {
                    "a" shouldBe  "a"
                }
            }
        }
    }
}