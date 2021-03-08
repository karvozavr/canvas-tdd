package com.github.karvozavr.canvas.app

import com.github.karvozavr.canvas.app.controller.CommandParser
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer
import org.junit.jupiter.api.Test

internal class CanvasCLIApplicationTest {

    @Test
    fun `should create new canvas`() {
        val app = CanvasCLIApplication(
            commandParser = CommandParser(),
            userInputProvider = TestUserInputProvider(listOf("C 5 3", "Q")),
            textOutputReceiver = TestTextOutputReceiver(listOf("> ", "     \n     \n     ", "> ")),
            canvasRenderer = AsciiCanvasRenderer()
        )

        app.start()
    }
}