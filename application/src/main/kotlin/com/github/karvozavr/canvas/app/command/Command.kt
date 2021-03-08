package com.github.karvozavr.canvas.app.command

import arrow.core.Either

interface Command<ErrorType> {

    fun execute(applicationState: ApplicationState): Either<ErrorType, ApplicationState>
}

data class CommandResult<Result>(val applicationState: ApplicationState, val result: Result)