package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue

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

            return DrawRectangleCommand(oneCorner, otherCorner, pixelValue)
        }
    }

    override fun draw(canvas: Canvas): Canvas {
        TODO("Not yet implemented")
    }
}