package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.*
import com.github.karvozavr.canvas.defaultCanvasOfSize
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DrawLineCommandTest {

    @Test
    fun `should draw horizontal line`() {
        // given
        val canvas = defaultCanvasOfSize(5, 3, PixelValue('.'))
        val command = DrawLineCommand.lineFromTo(
            from =CanvasPoint.of(1.row, 2.col),
            to = CanvasPoint.of(4.x, 1.y),
            pixelValue = PixelValue('x')
        )

        // when
        val canvasWithLine = command.draw(canvas)

        // then
        val expected = """
            .xxx.
            .....
            .....
        """.trimIndent()
        val renderedCanvas = AsciiCanvasRenderer()
            .renderCanvas(canvasWithLine)
            .canvasRows
            .joinToString(separator = "\n")

        renderedCanvas shouldBe expected
    }

    @Test
    fun `should support only horizontal lines`() {
        shouldThrow<RuntimeException> {
            DrawLineCommand.lineFromTo(CanvasPoint(1.row, 2.col), CanvasPoint(3.row, 4.col))
        }
    }

    @Test
    fun `should draw vertical line`() {
        // given
        val canvas = defaultCanvasOfSize(5, 3, PixelValue('.'))
        val command = DrawLineCommand.lineFromTo(
            from =CanvasPoint.of(1.row, 2.col),
            to = CanvasPoint.of(2.x, 3.y),
            pixelValue = PixelValue('x')
        )

        // when
        val canvasWithLine = command.draw(canvas)

        // then
        val expected = """
            .x...
            .x...
            .x...
        """.trimIndent()
        val renderedCanvas = AsciiCanvasRenderer()
            .renderCanvas(canvasWithLine)
            .canvasRows
            .joinToString(separator = "\n")

        renderedCanvas shouldBe expected
    }
}