package com.github.karvozavr.canvas.app.io

import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.command.bucket.BucketCommand
import com.github.karvozavr.canvas.app.command.createCanvas.CreateCanvasCommand
import com.github.karvozavr.canvas.app.command.drawLine.LineCommand
import com.github.karvozavr.canvas.app.command.drawRect.RectCommand
import com.github.karvozavr.canvas.app.command.quit.QuitCommand
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
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
            "B" -> parseBucketCommand(tokens)
            else -> null
        }
    }

    private fun parseDrawLineCommand(tokens: List<String>): LineCommand? {
        if (tokens.size != 5) return null

        val aX = tokens[1].toIntOrNull() ?: return null
        val aY = tokens[2].toIntOrNull() ?: return null

        val bX = tokens[3].toIntOrNull() ?: return null
        val bY = tokens[4].toIntOrNull() ?: return null

        val aIsValid = aX > 0 && aY > 0
        val bIsValid = bX > 0 && bY > 0
        return if (aIsValid && bIsValid) {
            LineCommand(from = CanvasPoint.of(aX.x, aY.y), to = CanvasPoint.of(bX.x, bY.y))
        } else {
            null
        }
    }

    private fun parseDrawRectCommand(tokens: List<String>): Command? {
        if (tokens.size != 5) return null

        val aX = tokens[1].toIntOrNull() ?: return null
        val aY = tokens[2].toIntOrNull() ?: return null

        val bX = tokens[3].toIntOrNull() ?: return null
        val bY = tokens[4].toIntOrNull() ?: return null

        val aIsValid = aX > 0 && aY > 0
        val bIsValid = bX > 0 && bY > 0
        return if (aIsValid && bIsValid) {
            RectCommand(oneCorner = CanvasPoint.of(aX.x, aY.y), otherCorner = CanvasPoint.of(bX.x, bY.y))
        } else {
            null
        }
    }

    private fun parseBucketCommand(tokens: List<String>): BucketCommand? {
        if (tokens.size != 4) return null

        val x = tokens[1].toIntOrNull() ?: return null
        val y = tokens[2].toIntOrNull() ?: return null

        val color = if (tokens[3].length == 1) tokens[3][0] else return null

        return if (x > 0 && y > 0) {
            return BucketCommand(CanvasPoint.of(x.x, y.y), PixelValue(color))
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
