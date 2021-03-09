package com.github.karvozavr.canvas.app.command

import arrow.core.Either
import com.github.karvozavr.canvas.app.ApplicationState

interface Command {

    fun execute(applicationState: ApplicationState): Either<CommandError, ApplicationState>
}

data class CommandResult<Result>(val applicationState: ApplicationState, val result: Result)

interface CommandError {

    fun errorText(): String
}