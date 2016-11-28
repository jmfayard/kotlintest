package io.kotlintest.specs

import io.kotlintest.ListStack
import io.kotlintest.matchers.be
import io.kotlintest.properties.Gen

class FreeSpecTest : FreeSpec() {
    init {
        "generators" - {
            "Gen.choose(3, 10)" - {
                val gen = Gen.choose(3, 10)
                val generated = (1..20).map { gen.generate() }
                "nb >= 3"  {
                    forAll(generated) { nb ->
                        nb should be gte 3
                    }
                }
                "nb < 10"  {
                    forAll(generated) { it should be lt 10 }
                }
                "forsome, nb = 3" {
                    forAtLeastOne(generated) { it == 3 }
                }
            }
        }
        "given a ListStack" - {
            "pop" - {
                "should remove the last element from stack" {
                    val stack = ListStack<String>()
                    stack.push("hello")
                    stack.push("world")
                    stack.size() shouldBe 2
                    stack.pop() shouldBe "world"
                    stack.size() shouldBe 1
                }.config(ignored = true)
            }
            "peek" - {
                "should leave the stack unmodified" {
                    val stack = ListStack<String>()
                    stack.push("hello")
                    stack.push("world")
                    stack.size() shouldBe 2
                    stack.peek() shouldBe "world"
                    stack.size() shouldBe 2
                }.config(ignored = true)
            }
        }

        "params" - {
            "support config" {
            }.config(invocations = 5, ignored = true)
        }
    }
}