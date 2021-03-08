package com.github.karvozavr.canvas.app.controller

import com.github.karvozavr.canvas.app.command.QuitCommand
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
    fun `should parse create command`() {
        val commandParser = CommandParser()

        val command = commandParser.parseCommand("C 20 4")

//        command shouldBe
    }
}