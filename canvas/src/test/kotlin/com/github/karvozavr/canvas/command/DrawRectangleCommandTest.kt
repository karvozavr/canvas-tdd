package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.canvas.x
import com.github.karvozavr.canvas.canvas.y
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
}