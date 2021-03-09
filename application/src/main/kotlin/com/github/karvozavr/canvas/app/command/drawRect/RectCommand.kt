package com.github.karvozavr.canvas.app.command.drawRect

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.github.karvozavr.canvas.app.ApplicationState
import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.command.CommandError
import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.command.DrawRectangleCommand

data class RectCommand(
    private val oneCorner: CanvasPoint,
    private val otherCorner: CanvasPoint
) : Command {

    override fun execute(applicationState: ApplicationState): Either<DrawRectError, ApplicationState> {
        if (applicationState.canvas == null) {
            return CanvasDoesNotExist.left()
        }

        if (
            !isPointOnCanvas(oneCorner, applicationState.canvas) ||
            !isPointOnCanvas(otherCorner, applicationState.canvas)
        ) {
            return PointIsOutOfCanvas.left()
        }

        val command = DrawRectangleCommand.rectWithCorners(oneCorner, otherCorner, PixelValue('x'))
        val newCanvas = command.draw(applicationState.canvas)
        return applicationState.copy(canvas = newCanvas).right()
    }

    private fun isPointOnCanvas(point: CanvasPoint, canvas: Canvas) =
        point.row.row <= canvas.height && point.column.column <= canvas.width
}

sealed class DrawRectError : CommandError

object CanvasDoesNotExist : DrawRectError() {
    override fun errorText(): String = "Canvas has to be created first"
}

object PointIsOutOfCanvas : DrawRectError() {
    override fun errorText(): String = "Point is out of canvas"
}