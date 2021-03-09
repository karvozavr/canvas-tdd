package com.github.karvozavr.canvas.app

import com.github.karvozavr.canvas.app.io.TextOutputReceiver
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.fail

class TestTextOutputReceiver(private val expectedOutputs: List<String>) : TextOutputReceiver {

    var idx = 0

    override fun print(textData: String) {
        if (idx < expectedOutputs.size) {
            textData shouldBe expectedOutputs[idx]
            idx++
        } else {
            fail("Unexpected output")
        }
    }
}
