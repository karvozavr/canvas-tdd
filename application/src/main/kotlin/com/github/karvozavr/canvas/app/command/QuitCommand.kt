package com.github.karvozavr.canvas.app.command

import arrow.core.Either
import com.github.karvozavr.canvas.app.TerminationException

class QuitCommand : Command<Unit> {

    override fun execute(applicationState: ApplicationState): Either<Nothing, ApplicationState> {
        throw TerminationException
    }
}