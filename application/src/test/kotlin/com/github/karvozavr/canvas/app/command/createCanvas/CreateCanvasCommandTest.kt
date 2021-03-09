package com.github.karvozavr.canvas.app.command.createCanvas

import com.github.karvozavr.canvas.app.emptyApplicationState
import io.kotest.assertions.arrow.either.shouldBeLeft
import io.kotest.assertions.arrow.either.shouldBeRight
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

internal class CreateCanvasCommandTest {

    @Test
    fun `should create canvas of given size for no canvas`() {
        val applicationState = emptyApplicationState()
        val command = CreateCanvasCommand(20, 10)
        val result = command.execute(applicationState)
        result.shouldBeRight()
        val newState = result.orNull() ?: fail()
        val canvas = newState.canvas.shouldNotBeNull()
        canvas.width shouldBe 20
        canvas.height shouldBe 10
    }

    @Test
    fun `should not create canvas of non-positive size`() {
        val applicationState = emptyApplicationState()
        val command = CreateCanvasCommand(0, -100)
        val result = command.execute(applicationState)
        result shouldBeLeft InvalidCanvasDimensions
    }
}