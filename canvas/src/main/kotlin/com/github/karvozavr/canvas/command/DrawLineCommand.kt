package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.*

data class DrawLineCommand internal constructor(
    val from: CanvasPoint,
    val to: CanvasPoint,
    val pixelValue: PixelValue
) : DrawingCommand {

    companion object {
        fun lineFromTo(
            from: CanvasPoint,
            to: CanvasPoint,
            pixelValue: PixelValue = PixelValue('x')
        ): DrawLineCommand {
            validate(from, to)
            return DrawLineCommand(from, to, pixelValue)
        }

        private fun validate(from: CanvasPoint, to: CanvasPoint) {
            if (from.row != to.row && from.column != to.column) {
                throw RuntimeException("Line should be either horizontal or vertical")
            }
        }
    }

    override fun draw(canvas: Canvas): Canvas = canvas.draw { setPixelAt ->
        if (from.row == to.row) {
            drawHorizontalLine(setPixelAt)
        } else if (from.column == to.column) {
            drawVerticalLine(setPixelAt)
        }
    }

    private fun drawHorizontalLine(setPixelAt: SetPixelAt) {
        val columnStart = minOf(from.column.column, to.column.column)
        val columnEnd = maxOf(from.column.column, to.column.column)
        (columnStart..columnEnd).map { setPixelAt(CanvasPoint.of(from.row, it.col), pixelValue) }
    }

    private fun drawVerticalLine(setPixelAt: SetPixelAt) {
        val rowStart = minOf(from.row.row, to.row.row)
        val rowEnd = maxOf(from.row.row, to.row.row)
        (rowStart..rowEnd).map { setPixelAt(CanvasPoint.of(it.row, from.column), pixelValue) }
    }
}
