package com.github.karvozavr.canvas.renderer

import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.defaultCanvasOfSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class StringCanvasRendererTest {

    @Test
    fun `should render default canvas of size 5x3`() {
        val canvas = defaultCanvasOfSize(width = 5, height = 3, defaultPixelValue = PixelValue('.'))

        val canvasRenderer = StringCanvasRenderer()

        val renderedCanvas = """
            .....
            .....
            .....
        """.trimIndent()
        canvasRenderer.renderCanvas(canvas) shouldBe CanvasAsString(renderedCanvas)
    }
}