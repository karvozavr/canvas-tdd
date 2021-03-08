package com.github.karvozavr.canvas.app.controller

import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.command.CreateCanvasCommand
import com.github.karvozavr.canvas.app.command.QuitCommand

class CommandParser {

    fun parseCommand(userInput: String): Command<out Any>? {
        val tokens = userInput.split(Regex("\\s+"))
        if (tokens.isEmpty()) return null

        val commandName = tokens[0]

        return when (commandName) {
            "Q" -> QuitCommand
            "C" -> parseCreateCanvasCommand(tokens)
            else -> null
        }
    }

    private fun parseCreateCanvasCommand(tokens: List<String>): CreateCanvasCommand? {
        if (tokens.size != 3) return null

        val width = tokens[1].toIntOrNull() ?: return null
        val height = tokens[2].toIntOrNull() ?: return null

        return CreateCanvasCommand(width = width, height = height)
    }
}