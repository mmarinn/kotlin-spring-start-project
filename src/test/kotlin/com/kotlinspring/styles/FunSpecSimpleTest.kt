package com.kotlinspring.styles

import io.kotest.assertions.json.shouldEqualJson
import io.kotest.assertions.json.shouldNotEqualJson
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.endWith
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.startWith

class FunSpecSimpleTest : FunSpec({
    test("name of tester should return the correct length") {
        val nameTester = "Matheus Marin"
        nameTester.shouldContain("Matheus")
        nameTester.length shouldBe 13
        nameTester should startWith("Matheus")
        nameTester should endWith("Marin")
    }
    test("a json with a developer should be valid") {
        val json = """ { "age" : 23, "name": "matheus", "location": "sao paulo" } """
        json.shouldEqualJson(returnJsonOfAValidDev())
    }

    test("a json with a PO should be invalid") {
        val json = """ { "age" : 45, "name": "robert", "location": "rio de janeiro" } """
        json.shouldNotEqualJson(returnJsonOfAValidDev())
    }

}) {
    companion object {
        fun returnJsonOfAValidDev() : String{
            return  """ { "age" : 23, "name": "matheus", "location": "sao paulo" } """
        }
    }

}