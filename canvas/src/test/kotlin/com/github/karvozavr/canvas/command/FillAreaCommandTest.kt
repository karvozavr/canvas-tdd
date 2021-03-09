package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.canvas.x
import com.github.karvozavr.canvas.canvas.y
import com.github.karvozavr.canvas.canvasFromString
import com.github.karvozavr.canvas.defaultCanvasOfSize
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class FillAreaCommandTest {

    @Test
    fun `should create command`() {
        val initialPoint = CanvasPoint.of(14.x, 1.y)
        val command = FillAreaCommand.fillArea(initialPoint = initialPoint, pixelValue = PixelValue('o'))

        command.initialPoint shouldBe initialPoint
        command.pixelValue shouldBe PixelValue('o')
    }

    @Test
    fun `should fill the area with color`() {
        val canvas = canvasFromString(
            """
            ......
            .xxxx.
            .x..x.
            .xxxx.
            ......
            ..xx..
        """.trimIndent()
        )
        val command = FillAreaCommand(initialPoint = CanvasPoint.of(2.x, 5.y), pixelValue = PixelValue('o'))

        val newCanvas = command.draw(canvas)

        val expected = """
            oooooo
            oxxxxo
            ox..xo
            oxxxxo
            oooooo
            ooxxoo
        """.trimIndent()
        AsciiCanvasRenderer().renderCanvas(newCanvas).toString() shouldBe expected
    }

    @Test
    fun `should fill the area with the same color`() {
        val canvas = canvasFromString(
            """
            ......
            .xxxx.
            .x..x.
            .xxxx.
            ......
            ..xx..
        """.trimIndent()
        )
        val command = FillAreaCommand(initialPoint = CanvasPoint.of(2.x, 5.y), pixelValue = PixelValue('.'))

        val newCanvas = command.draw(canvas)

        val expected = """
            ......
            .xxxx.
            .x..x.
            .xxxx.
            ......
            ..xx..
        """.trimIndent()
        AsciiCanvasRenderer().renderCanvas(newCanvas).toString() shouldBe expected
    }

    @Test
    fun `should fill the area`() {
        val canvas = defaultCanvasOfSize(5, 5, PixelValue('.'))
        val command = FillAreaCommand(initialPoint = CanvasPoint.of(2.x, 5.y), pixelValue = PixelValue('o'))

        val newCanvas = command.draw(canvas)

        val expected = """
            ooooo
            ooooo
            ooooo
            ooooo
            ooooo
        """.trimIndent()
        AsciiCanvasRenderer().renderCanvas(newCanvas).toString() shouldBe expected
    }
}
