package com.github.karvozavr.canvas.app.command.bucket

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.github.karvozavr.canvas.app.ApplicationState
import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.command.CommandError
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.command.FillAreaCommand
import com.github.karvozavr.canvas.isPointOnCanvas

data class BucketCommand(
    private val initialPoint: CanvasPoint,
    private val color: PixelValue
) : Command {

    override fun execute(applicationState: ApplicationState): Either<CommandError, ApplicationState> {
        if (applicationState.canvas == null) {
            return CanvasDoesNotExist.left()
        }

        if (!isPointOnCanvas(initialPoint, applicationState.canvas)) {
            return PointIsOutOfCanvas.left()
        }

        val command = FillAreaCommand.fillArea(initialPoint = initialPoint, pixelValue = color)
        val newCanvas = command.draw(applicationState.canvas)
        return applicationState.copy(canvas = newCanvas).right()
    }
}

sealed class BucketCommandError : CommandError

object CanvasDoesNotExist : BucketCommandError() {
    override fun errorText(): String = "Canvas has to be created first"
}

object PointIsOutOfCanvas : BucketCommandError() {
    override fun errorText(): String = "Point is out of canvas"
}