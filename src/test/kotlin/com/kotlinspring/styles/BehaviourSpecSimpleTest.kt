package com.kotlinspring.styles

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAtLeast
import io.kotest.inspectors.forNone
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldHaveMinLength

class BehaviourSpecSimpleTest : BehaviorSpec() {
    init {
        given("a list of names") {
            val listOfNames = listOf("sam", "bbrio", "timotthy", "samuel jackson")
            `when`("the list contains sam") {
                listOfNames.shouldContain("sam")
                then("should have at least 2 names with min lenght 7") {
                    listOfNames.forAtLeast(2) {
                        it.shouldHaveMinLength(7)
                    }
                }
                and("should have a valid name") {
                    listOfNames.forNone {
                        it.shouldContain("x")
                    }
                }
            }
        }
    }
}