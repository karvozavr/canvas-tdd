package com.github.karvozavr.canvas.canvas

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CoordinatesTest {

    @Test
    fun `should create coordinates with correct values`() {
        3.row.row shouldBe 3
        42.col.column shouldBe 42
        12.x.x shouldBe 12
        1.y.y shouldBe 1
    }

    @Test
    fun `should report error for non-positive values`() {
        shouldThrow<RuntimeException> { 0.row }
        shouldThrow<RuntimeException> { (-1).col }
        shouldThrow<RuntimeException> { (-42).x }
        shouldThrow<RuntimeException> { 0.y }
    }
}