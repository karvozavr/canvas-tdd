package com.github.karvozavr.canvas.app.command

import com.github.karvozavr.canvas.app.ApplicationState
import com.github.karvozavr.canvas.app.defaultCanvasOfSize
import com.github.karvozavr.canvas.canvas.*
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer
import io.kotest.assertions.arrow.either.shouldBeRight
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class LineCommandTest {

    @Test
    fun `should draw line`() {
        // given
        val canvas = defaultCanvasOfSize(5, 3, PixelValue('.'))
        val appState = ApplicationState(canvas)
        val command = LineCommand(from = CanvasPoint.of(1.row, 2.col), to = CanvasPoint.of(4.x, 1.y))

        // when
        val newState = command.execute(appState)
        newState.shouldBeRight()

        val newCanvas = newState.orNull()?.canvas.shouldNotBeNull()

        // then
        val expected = """
            .xxx.
            .....
            .....
        """.trimIndent()
        AsciiCanvasRenderer().renderCanvas(newCanvas).toString() shouldBe expected
    }
}
