package com.github.karvozavr.canvas.app.command

import arrow.core.Either

data class CreateCanvasCommand(val width: Int, val height: Int) : Command<CreateCanvasError> {

    override fun execute(applicationState: ApplicationState): Either<CreateCanvasError, ApplicationState> {
        TODO("Not yet implemented")
    }
}

sealed class CreateCanvasError

object NonPositiveDimensions : CreateCanvasError()