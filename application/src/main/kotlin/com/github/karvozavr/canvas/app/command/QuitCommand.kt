package com.github.karvozavr.canvas.app.command

import arrow.core.Either
import com.github.karvozavr.canvas.app.ApplicationState
import com.github.karvozavr.canvas.app.TerminationException

object QuitCommand : Command {

    override fun execute(applicationState: ApplicationState): Either<Nothing, ApplicationState> {
        throw TerminationException
    }
}
