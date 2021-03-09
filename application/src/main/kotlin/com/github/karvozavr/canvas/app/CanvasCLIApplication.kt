package com.github.karvozavr.canvas.app

import arrow.core.Either
import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.command.CommandError
import com.github.karvozavr.canvas.app.io.CommandParser
import com.github.karvozavr.canvas.app.io.TextOutputReceiver
import com.github.karvozavr.canvas.app.io.UserInputProvider
import com.github.karvozavr.canvas.app.view.CanvasPresenter
import com.github.karvozavr.canvas.app.view.CanvasTextView

const val INVALID_COMMAND_MESSAGE = "Invalid command"
const val CLI_PROMPT = "enter command: "

class CanvasCLIApplication(
    private val commandParser: CommandParser,
    private val userInputProvider: UserInputProvider,
    private val textOutputReceiver: TextOutputReceiver,
    private val textErrorOutputReceiver: TextOutputReceiver,
    private val canvasPresenter: CanvasPresenter<CanvasTextView>
) {

    fun start() {
        var applicationState = ApplicationState(null)

        while (true) {
            getCommandFromUser()?.let { command ->
                try {
                    val result = command.execute(applicationState)
                    applicationState = processCommandResult(result) ?: applicationState
                } catch (e: TerminationException) {
                    return
                }
            } ?: reportParsingError()
        }
    }

    private fun processCommandResult(result: Either<CommandError, ApplicationState>): ApplicationState? =
        result
            .mapLeft {
                textErrorOutputReceiver.println(it.errorText())
            }
            .map {
                val canvasTextView = canvasPresenter.present(it.canvas!!)
                textOutputReceiver.println(canvasTextView.text)
                it
            }.orNull()

    private fun reportParsingError() {
        textErrorOutputReceiver.println(INVALID_COMMAND_MESSAGE)
    }

    private fun getCommandFromUser(): Command? {
        textOutputReceiver.print(CLI_PROMPT)
        val userInput = userInputProvider.getUserInput() ?: return null
        return commandParser.parseCommand(userInput)
    }
}
