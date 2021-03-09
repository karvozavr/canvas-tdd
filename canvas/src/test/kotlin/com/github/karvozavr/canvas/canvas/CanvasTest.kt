package com.github.karvozavr.canvas.canvas

import com.github.karvozavr.canvas.defaultCanvasOfSize
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CanvasTest {

    @Test
    fun `should create empty canvas 20x10`() {
        val canvas = Canvas.ofSize(width = 20, height = 10)
        canvas.shouldNotBeNull()

        canvas.width shouldBe 20
        canvas.height shouldBe 10
    }

    @Test
    fun `should not create canvas of invalid size`() {
        shouldThrow<RuntimeException> {
            Canvas.ofSize(width = 0, height = 10)
        }
        shouldThrow<RuntimeException> {
            Canvas.ofSize(width = 1, height = -1)
        }
    }

    @Test
    fun `should have default pixel value in all pixels`() {
        val canvas = defaultCanvasOfSize(5, 3, defaultPixelValue = PixelValue('a'))

        for (row in 1..3) {
            for (col in 1..5) {
                canvas.pixelAt(CanvasPoint.of(row.row, col.col)) shouldBe PixelValue('a')
            }
        }
    }

    @Test
    fun `should draw new pixels on canvas`() {
        val canvas = defaultCanvasOfSize(2, 2, defaultPixelValue = PixelValue('.'))

        val canvasWithDrawing = canvas.draw { setPixelAt, _ ->
            setPixelAt(CanvasPoint.of(1.row, 2.col), PixelValue('a'))
            setPixelAt(CanvasPoint.of(2.row, 1.col), PixelValue('b'))
        }

        canvasWithDrawing.let {
            it.pixelAt(CanvasPoint.of(1.row, 1.col)) shouldBe PixelValue('.')
            it.pixelAt(CanvasPoint.of(1.row, 2.col)) shouldBe PixelValue('a')
            it.pixelAt(CanvasPoint.of(2.row, 1.col)) shouldBe PixelValue('b')
            it.pixelAt(CanvasPoint.of(2.row, 2.col)) shouldBe PixelValue('.')
        }

        canvas.forEachPixel { _, value -> value shouldBe PixelValue('.') }
    }

    @Test
    fun `should return consistent values from getPixelAt while drawing`() {
        val canvas = defaultCanvasOfSize(2, 1, defaultPixelValue = PixelValue('.'))

        val canvasWithDrawing = canvas.draw { setPixelAt, getPixelAt ->
            setPixelAt(CanvasPoint.of(1.row, 2.col), PixelValue('a'))
            getPixelAt(CanvasPoint.of(1.row, 2.col)) shouldBe PixelValue('a')
        }

        canvasWithDrawing.let {
            it.pixelAt(CanvasPoint.of(1.row, 1.col)) shouldBe PixelValue('.')
            it.pixelAt(CanvasPoint.of(1.row, 2.col)) shouldBe PixelValue('a')
        }

        canvas.forEachPixel { _, value -> value shouldBe PixelValue('.') }
    }
}
