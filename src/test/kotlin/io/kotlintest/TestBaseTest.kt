package io.kotlintest

import io.kotlintest.specs.StringSpec
import java.io.Closeable

class TestBaseTest : StringSpec() {

    private val resourceA = autoClose(Checker)
    private val resourceB = autoClose(Closeable2)
    private val resourceC = autoClose(Closeable1)

    init {
        "should close resources in reverse order" {
            // nothing to do here
            "should not work here" {
                fail("but it worked!")
            }
        }
    }

    private object Closeable1 : Closeable {

        var closed = false

        override fun close() {
            closed = true
        }
    }

    private object Closeable2 : Closeable {

        var closed = false

        override fun close() {
            assert(Closeable1.closed)
            closed = true
        }
    }

    private object Checker : Closeable {
        override fun close() {
            assert(Closeable1.closed && Closeable2.closed)
        }

    }
}