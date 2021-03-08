package com.github.karvozavr.canvas.renderer

import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.canvas.col
import com.github.karvozavr.canvas.canvas.row
import com.github.karvozavr.canvas.defaultCanvasOfSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class AsciiCanvasRendererTest {

    @Test
    fun `should render default canvas of size 5x3`() {
        val canvas = defaultCanvasOfSize(width = 5, height = 3, defaultPixelValue = PixelValue('.'))

        val expected = """
            .....
            .....
            .....
        """.trimIndent()
        val renderedCanvas = AsciiCanvasRenderer()
            .renderCanvas(canvas)
            .canvasRows
            .joinToString(separator = "\n")

        renderedCanvas shouldBe expected
    }

    @Test
    fun `should render canvas with drawing`() {
        val canvas = defaultCanvasOfSize(5, 3, PixelValue('.'))

        val canvasWithDrawing = canvas.draw { setPixelAt ->
            setPixelAt(2.row, 3.col, PixelValue('x'))
            setPixelAt(2.row, 4.col, PixelValue('o'))
            setPixelAt(3.row, 1.col, PixelValue('a'))
        }

        val expected = """
            .....
            ..xo.
            a....
        """.trimIndent()
        val renderedCanvas = AsciiCanvasRenderer()
            .renderCanvas(canvasWithDrawing)
            .canvasRows
            .joinToString(separator = "\n")

        renderedCanvas shouldBe expected
    }
}