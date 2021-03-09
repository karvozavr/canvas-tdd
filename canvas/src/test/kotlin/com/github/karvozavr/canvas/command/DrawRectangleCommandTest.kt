package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.*
import com.github.karvozavr.canvas.defaultCanvasOfSize
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DrawRectangleCommandTest {

    @Test
    fun `should create command`() {
        val upperLeft = CanvasPoint.of(14.x, 1.y)
        val lowerRight = CanvasPoint.of(16.x, 3.y)
        val command = DrawRectangleCommand.rectWithCorners(
            oneCorner = upperLeft,
            otherCorner = lowerRight
        )

        command.upperLeft shouldBe upperLeft
        command.lowerRight shouldBe lowerRight
        command.pixelValue shouldBe PixelValue('x')
    }

    @Test
    fun `should create correct rectangle from lower left and upper right`() {
        val lowerLeft = CanvasPoint.of(14.x, 10.y)
        val upperRight = CanvasPoint.of(16.x, 1.y)
        val command = DrawRectangleCommand.rectWithCorners(
            oneCorner = lowerLeft,
            otherCorner = upperRight
        )

        command.upperLeft shouldBe CanvasPoint.of(14.x, 1.y)
        command.lowerRight shouldBe CanvasPoint.of(16.x, 10.y)
        command.pixelValue shouldBe PixelValue('x')
    }

    @Test
    fun `should draw a rectangle`() {
        val canvas = defaultCanvasOfSize(6, 6, PixelValue('.'))
        val command = DrawRectangleCommand.rectWithCorners(
            oneCorner = CanvasPoint(2.row, 2.col),
            otherCorner = CanvasPoint(4.row, 5.col)
        )

        val canvasWithRectangle = command.draw(canvas)

        val expected = """
            ......
            .xxxx.
            .x..x.
            .xxxx.
            ......
            ......
        """.trimIndent()
        AsciiCanvasRenderer().renderCanvas(canvasWithRectangle).toString() shouldBe expected
    }
}