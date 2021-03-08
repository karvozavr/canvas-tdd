package com.github.karvozavr.canvas.app.command

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.github.karvozavr.canvas.canvas.Canvas

data class CreateCanvasCommand(val width: Int, val height: Int) : Command<CreateCanvasError> {

    override fun execute(applicationState: ApplicationState): Either<CreateCanvasError, ApplicationState> {
        if (width <= 0 && height <= 0) {
            return InvalidCanvasDimensions.left()
        }

        return ApplicationState(Canvas.ofSize(width, height)).right()
    }
}

sealed class CreateCanvasError

object InvalidCanvasDimensions : CreateCanvasError()