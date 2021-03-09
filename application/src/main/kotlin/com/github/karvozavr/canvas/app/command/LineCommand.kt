package com.github.karvozavr.canvas.app.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.github.karvozavr.canvas.app.ApplicationState
import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.command.DrawLineCommand

class LineCommand(
    private val from: CanvasPoint,
    private val to: CanvasPoint
) : Command {

    override fun execute(applicationState: ApplicationState): Either<DrawLineError, ApplicationState> {
        if (applicationState.canvas == null) {
            return CanvasDoesNotExist.left()
        }

        val lineIsNotHorizontalOrVertical = from.row != to.row && from.column != to.column
        if (lineIsNotHorizontalOrVertical) {
            return LineIsNotHorizontalOrVertical.left()
        }

        if (!isPointOnCanvas(from, applicationState.canvas) || !isPointOnCanvas(to, applicationState.canvas)) {
            return PointIsOutOfCanvas.left()
        }

        val command = DrawLineCommand.lineFromTo(from, to, PixelValue('x'))
        val newCanvas = command.draw(applicationState.canvas)
        return applicationState.copy(canvas = newCanvas).right()
    }

    private fun isPointOnCanvas(point: CanvasPoint, canvas: Canvas) =
        point.row.row <= canvas.height && point.column.column <= canvas.width
}

sealed class DrawLineError : CommandError

object LineIsNotHorizontalOrVertical : DrawLineError() {
    override fun errorText(): String = "Line has to be either horizontal or vertical"
}

object CanvasDoesNotExist : DrawLineError() {
    override fun errorText(): String = "Canvas has to be created first"
}

object PointIsOutOfCanvas : DrawLineError() {
    override fun errorText(): String = "Point is out of canvas"
}
