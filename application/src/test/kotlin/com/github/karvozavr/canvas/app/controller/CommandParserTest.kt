package com.github.karvozavr.canvas.app.controller

import com.github.karvozavr.canvas.app.command.QuitCommand
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test

internal class CommandParserTest {

    @Test
    fun `should parse quit command`() {
        val commandParser = CommandParser()

        val command = commandParser.parseCommand("Q")

        command.shouldNotBeNull()
        command.shouldBeTypeOf<QuitCommand>()
    }
}