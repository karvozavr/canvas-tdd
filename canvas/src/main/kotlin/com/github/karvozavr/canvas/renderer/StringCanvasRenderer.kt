package com.github.karvozavr.canvas.renderer

import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.col
import com.github.karvozavr.canvas.canvas.row

inline class CanvasAscii(val canvasRows: List<String>)

class StringCanvasRenderer : CanvasRenderer<CanvasAscii> {

    override fun renderCanvas(canvas: Canvas): CanvasAscii {
        val renderedCanvas = (1..canvas.height).map { row ->
                (1..canvas.width).joinToString(separator = "") { col ->
                    canvas.pixelAt(row.row, col.col).value.toString()
                }
            }

        return CanvasAscii(renderedCanvas)
    }
}
