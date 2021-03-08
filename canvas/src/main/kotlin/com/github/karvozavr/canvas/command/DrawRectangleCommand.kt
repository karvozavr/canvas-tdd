package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.*
import javax.naming.CannotProceedException
import kotlin.math.max
import kotlin.math.min

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
        TODO("Not yet implemented")
    }
}