package com.github.karvozavr.canvas.app

import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.command.CommandError
import com.github.karvozavr.canvas.app.controller.CommandParser
import com.github.karvozavr.canvas.app.controller.TextOutputReceiver
import com.github.karvozavr.canvas.app.controller.UserInputProvider
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
            val command = getCommandFromUser()
            if (command == null) {
                reportParsingError()
                continue
            }

            val result = try {
                command.execute(applicationState)
            } catch (e: TerminationException) {
                break
            }

            result
                .mapLeft {
                    textErrorOutputReceiver.println(it.errorText())
                }
                .map {
                    applicationState = it
                    val canvasTextView = canvasPresenter.present(applicationState.canvas!!)
                    textOutputReceiver.println(canvasTextView.text)
                }
        }
    }

    private fun reportParsingError() {
        textErrorOutputReceiver.println(INVALID_COMMAND_MESSAGE)
    }

    private fun getCommandFromUser(): Command? {
        textOutputReceiver.print(CLI_PROMPT)
        val userInput = userInputProvider.getUserInput() ?: return null
        return commandParser.parseCommand(userInput)
    }
}