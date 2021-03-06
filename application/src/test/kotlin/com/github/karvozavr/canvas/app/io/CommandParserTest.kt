package com.github.karvozavr.canvas.app.io

import com.github.karvozavr.canvas.app.command.bucket.BucketCommand
import com.github.karvozavr.canvas.app.command.createCanvas.CreateCanvasCommand
import com.github.karvozavr.canvas.app.command.drawLine.LineCommand
import com.github.karvozavr.canvas.app.command.drawRect.RectCommand
import com.github.karvozavr.canvas.app.command.quit.QuitCommand
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.canvas.x
import com.github.karvozavr.canvas.canvas.y
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CommandParserTest {

    @Test
    fun `should parse quit command`() {
        val commandParser = CommandParser()

        val command = commandParser.parseCommand("Q")

        command shouldBe QuitCommand
    }

    @Test
    fun `should parse CreateCanvas command`() {
        val commandParser = CommandParser()

        val command = commandParser.parseCommand("C 20 4")

        command shouldBe CreateCanvasCommand(width = 20, height = 4)
    }

    @Test
    fun `should parse Line command`() {
        val commandParser = CommandParser()

        val command = commandParser.parseCommand("L 2 3 5 3")

        command shouldBe LineCommand(CanvasPoint.of(2.x, 3.y), CanvasPoint.of(5.x, 3.y))
    }

    @Test
    fun `should parse Rect command`() {
        val commandParser = CommandParser()

        val command = commandParser.parseCommand("R 2 3 5 4")

        command shouldBe RectCommand(CanvasPoint.of(2.x, 3.y), CanvasPoint.of(5.x, 4.y))
    }

    @Test
    fun `should parse Bucket command`() {
        val commandParser = CommandParser()

        val command = commandParser.parseCommand("B 2 3 o")

        command shouldBe BucketCommand(CanvasPoint.of(2.x, 3.y), PixelValue('o'))
    }

    @Test
    fun `should not parse unknown command`() {
        val commandParser = CommandParser()

        val command = commandParser.parseCommand("%$@!@AA")

        command.shouldBeNull()
    }
}
