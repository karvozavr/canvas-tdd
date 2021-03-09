package com.github.karvozavr.canvas.app.controller

import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.command.createCanvas.CreateCanvasCommand
import com.github.karvozavr.canvas.app.command.drawLine.LineCommand
import com.github.karvozavr.canvas.app.command.drawRect.RectCommand
import com.github.karvozavr.canvas.app.command.quit.QuitCommand
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.x
import com.github.karvozavr.canvas.canvas.y

class CommandParser {

    fun parseCommand(userInput: String): Command? {
        val tokens = userInput.split(Regex("\\s+"))
        if (tokens.isEmpty()) return null

        val commandName = tokens[0]
        return when (commandName) {
            "Q" -> QuitCommand
            "C" -> parseCreateCanvasCommand(tokens)
            "L" -> parseDrawLineCommand(tokens)
            "R" -> parseDrawRectCommand(tokens)
            else -> null
        }
    }

    private fun parseDrawRectCommand(tokens: List<String>): Command? {
        if (tokens.size != 5) return null

        val aX = tokens[1].toIntOrNull() ?: return null
        val aY = tokens[2].toIntOrNull() ?: return null

        val bX = tokens[3].toIntOrNull() ?: return null
        val bY = tokens[4].toIntOrNull() ?: return null

        return if (aX > 0 && bX > 0 && aY > 0 && bY > 0) {
            RectCommand(oneCorner = CanvasPoint.of(aX.x, aY.y), otherCorner = CanvasPoint.of(bX.x, bY.y))
        } else {
            null
        }
    }

    private fun parseDrawLineCommand(tokens: List<String>): LineCommand? {
        if (tokens.size != 5) return null

        val aX = tokens[1].toIntOrNull() ?: return null
        val aY = tokens[2].toIntOrNull() ?: return null

        val bX = tokens[3].toIntOrNull() ?: return null
        val bY = tokens[4].toIntOrNull() ?: return null

        return if (aX > 0 && bX > 0 && aY > 0 && bY > 0) {
            LineCommand(from = CanvasPoint.of(aX.x, aY.y), to = CanvasPoint.of(bX.x, bY.y))
        } else {
            null
        }
    }

    private fun parseCreateCanvasCommand(tokens: List<String>): CreateCanvasCommand? {
        if (tokens.size != 3) return null

        val width = tokens[1].toIntOrNull() ?: return null
        val height = tokens[2].toIntOrNull() ?: return null

        return CreateCanvasCommand(width = width, height = height)
    }
}