package com.github.karvozavr.canvas.app.controller

import com.github.karvozavr.canvas.app.command.Command
import com.github.karvozavr.canvas.app.command.QuitCommand

class CommandParser {

    fun parseCommand(userInput: String): Command<out Any>? {
        return QuitCommand
    }
}