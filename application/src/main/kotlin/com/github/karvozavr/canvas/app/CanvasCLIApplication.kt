package com.github.karvozavr.canvas.app

import com.github.karvozavr.canvas.app.command.ApplicationState
import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.controller.CommandParser
import com.github.karvozavr.canvas.app.controller.TextOutputReceiver
import com.github.karvozavr.canvas.app.controller.UserInputProvider
import com.github.karvozavr.canvas.app.view.CanvasPresenter
import com.github.karvozavr.canvas.app.view.CanvasTextView

class CanvasCLIApplication(
    private val commandParser: CommandParser,
    private val userInputProvider: UserInputProvider,
    private val textOutputReceiver: TextOutputReceiver,
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

            result.map {
                applicationState = it
                val canvasTextView = canvasPresenter.present(applicationState.canvas!!)
                textOutputReceiver.println(canvasTextView.text)
            }
        }
    }

    private fun reportParsingError() {
        TODO("Not implemented")
    }

    private fun getCommandFromUser(): Command<out Any>? {
        textOutputReceiver.print("> ")
        val userInput = userInputProvider.getUserInput() ?: return null
        return commandParser.parseCommand(userInput)
    }
}