package com.github.karvozavr.canvas.app

import com.github.karvozavr.canvas.app.controller.CommandParser
import com.github.karvozavr.canvas.app.view.AsciiFramedCanvasPresenter
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer
import org.junit.jupiter.api.Test

internal class CanvasCLIApplicationTest {

    @Test
    fun `should create new canvas`() {
        val expectedCanvas = """
            -------
            |     |
            |     |
            |     |
            -------

        """.trimIndent()

        val app = CanvasCLIApplication(
            commandParser = CommandParser(),
            userInputProvider = TestUserInputProvider(listOf("C 5 3", "Q")),
            textOutputReceiver = TestTextOutputReceiver(listOf("> ", expectedCanvas, "> ")),
            canvasPresenter = AsciiFramedCanvasPresenter(AsciiCanvasRenderer())
        )

        app.start()
    }

    @Test
    fun `should report parsing error and continue`() {
        val app = CanvasCLIApplication(
            commandParser = CommandParser(),
            userInputProvider = TestUserInputProvider(listOf("42a sfd as aaaaaa saf", "Q")),
            textOutputReceiver = TestTextOutputReceiver(listOf("> ", "$INVALID_COMMAND_MESSAGE\n", "> ")),
            canvasPresenter = AsciiFramedCanvasPresenter(AsciiCanvasRenderer())
        )

        app.start()
    }
}