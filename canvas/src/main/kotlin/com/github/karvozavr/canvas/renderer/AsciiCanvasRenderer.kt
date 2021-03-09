package com.github.karvozavr.canvas.renderer

import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.col
import com.github.karvozavr.canvas.canvas.row

inline class CanvasAscii(val canvasRows: List<String>) {

    override fun toString(): String {
        return canvasRows.joinToString("\n")
    }
}

class AsciiCanvasRenderer : CanvasRenderer<CanvasAscii> {

    override fun renderCanvas(canvas: Canvas): CanvasAscii {
        val renderedCanvas = (1..canvas.height).map { row ->
                (1..canvas.width).joinToString(separator = "") { col ->
                    canvas.pixelAt(CanvasPoint.of(row.row, col.col)).value.toString()
                }
            }

        return CanvasAscii(renderedCanvas)
    }
}
