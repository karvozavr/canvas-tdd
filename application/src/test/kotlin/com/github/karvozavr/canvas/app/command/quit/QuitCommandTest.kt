package com.github.karvozavr.canvas.app.command.quit

import com.github.karvozavr.canvas.app.TerminationException
import com.github.karvozavr.canvas.app.emptyApplicationState
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

internal class QuitCommandTest {

    @Test
    fun `should terminate on quit command execution`() {
        val quitCommand = QuitCommand
        shouldThrow<TerminationException> {
            quitCommand.execute(emptyApplicationState())
        }
    }
}
