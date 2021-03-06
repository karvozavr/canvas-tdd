package com.github.karvozavr.canvas.app.command.drawLine

import com.github.karvozavr.canvas.app.ApplicationState
import com.github.karvozavr.canvas.app.defaultCanvasOfSize
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.canvas.col
import com.github.karvozavr.canvas.canvas.row
import com.github.karvozavr.canvas.canvas.x
import com.github.karvozavr.canvas.canvas.y
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer
import io.kotest.assertions.arrow.either.shouldBeLeft
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

    @Test
    fun `should report error for diagonal line`() {
        val canvas = defaultCanvasOfSize(5, 5, PixelValue('.'))
        val appState = ApplicationState(canvas)
        val command = LineCommand(from = CanvasPoint.of(1.row, 2.col), to = CanvasPoint.of(3.x, 4.y))

        command.execute(appState).shouldBeLeft(LineIsNotHorizontalOrVertical)
    }

    @Test
    fun `should report error for non-existing canvas`() {
        val appState = ApplicationState(null)
        val command = LineCommand(from = CanvasPoint.of(1.row, 2.col), to = CanvasPoint.of(3.x, 4.y))

        command.execute(appState).shouldBeLeft(CanvasDoesNotExist)
    }

    @Test
    fun `should report error for point out of canvas`() {
        val canvas = defaultCanvasOfSize(5, 5, PixelValue('.'))
        val appState = ApplicationState(canvas)
        val command = LineCommand(from = CanvasPoint.of(42.row, 2.col), to = CanvasPoint.of(2.x, 42.y))

        command.execute(appState).shouldBeLeft(PointIsOutOfCanvas)
    }
}
