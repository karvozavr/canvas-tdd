package com.github.karvozavr.canvas.app.command.quit

import arrow.core.Either
import com.github.karvozavr.canvas.app.ApplicationState
import com.github.karvozavr.canvas.app.TerminationException
import com.github.karvozavr.canvas.app.command.Command

object QuitCommand : Command {

    override fun execute(applicationState: ApplicationState): Either<Nothing, ApplicationState> {
        throw TerminationException
    }
}
