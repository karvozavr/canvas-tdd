package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.*

class DrawRectangleCommand internal constructor(
    val upperLeft: CanvasPoint,
    val lowerRight: CanvasPoint,
    val pixelValue: PixelValue
) : DrawingCommand {

    companion object {
        fun rectWithCorners(
            oneCorner: CanvasPoint,
            otherCorner: CanvasPoint,
            pixelValue: PixelValue = PixelValue('x')
        ): DrawRectangleCommand {
            val upperLeft = CanvasPoint.of(
                minOf(oneCorner.row.row, otherCorner.row.row).row,
                minOf(oneCorner.column.column, otherCorner.column.column).col
            )
            val lowerRight = CanvasPoint.of(
                maxOf(oneCorner.row.row, otherCorner.row.row).row,
                maxOf(oneCorner.column.column, otherCorner.column.column).col
            )

            return DrawRectangleCommand(upperLeft, lowerRight, pixelValue)
        }
    }

    override fun draw(canvas: Canvas): Canvas {
        val top = DrawLineCommand.lineFromTo(upperLeft, CanvasPoint(upperLeft.row, lowerRight.column))
        val bottom = DrawLineCommand.lineFromTo(CanvasPoint(lowerRight.row, upperLeft.column), lowerRight)
        val left = DrawLineCommand.lineFromTo(upperLeft, CanvasPoint(lowerRight.row, upperLeft.column))
        val right = DrawLineCommand.lineFromTo(lowerRight, CanvasPoint(upperLeft.row, lowerRight.column))

        return canvas.pipe(
            top::draw,
            bottom::draw,
            left::draw,
            right::draw
        )
    }
}

fun <T> T.pipe(vararg functions: (T) -> T): T =
    functions.fold(this) { value, f -> f(value) }
