package com.github.karvozavr.canvas.renderer

import com.github.karvozavr.canvas.canvas.Canvas

inline class CanvasAsString(val renderedCanvas: String)

class StringCanvasRenderer : CanvasRenderer<CanvasAsString> {

    override fun renderCanvas(canvas: Canvas): CanvasAsString {
        val renderedCanvas = """
            .....
            .....
            .....
        """.trimIndent()
        return CanvasAsString(renderedCanvas)
    }
}
