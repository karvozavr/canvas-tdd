package com.github.karvozavr.canvas.app

import com.github.karvozavr.canvas.app.command.ApplicationState
import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.controller.CommandParser
import com.github.karvozavr.canvas.app.controller.TextOutputReceiver
import com.github.karvozavr.canvas.app.controller.UserInputProvider
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer

class CanvasCLIApplication(
    private val commandParser: CommandParser,
    private val userInputProvider: UserInputProvider,
    private val textOutputReceiver: TextOutputReceiver,
    private val canvasRenderer: AsciiCanvasRenderer
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
                val rendered = canvasRenderer.renderCanvas(applicationState.canvas!!).canvasRows.joinToString("\n")
                textOutputReceiver.print(rendered) // TODO
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