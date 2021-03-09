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
            textOutputReceiver = TestTextOutputReceiver(listOf(CLI_PROMPT, expectedCanvas, CLI_PROMPT)),
            textErrorOutputReceiver = TestTextOutputReceiver(emptyList()),
            canvasPresenter = AsciiFramedCanvasPresenter(AsciiCanvasRenderer())
        )

        app.start()
    }

    @Test
    fun `should report parsing error and continue`() {
        val app = CanvasCLIApplication(
            commandParser = CommandParser(),
            userInputProvider = TestUserInputProvider(listOf("42a sfd as aaaaaa saf", "Q")),
            textOutputReceiver = TestTextOutputReceiver(listOf(CLI_PROMPT, CLI_PROMPT)),
            textErrorOutputReceiver = TestTextOutputReceiver(listOf("$INVALID_COMMAND_MESSAGE\n")),
            canvasPresenter = AsciiFramedCanvasPresenter(AsciiCanvasRenderer())
        )

        app.start()
    }

    @Test
    fun `should draw 2 lines, rect and fill after it`() {
        val userInputProvider = TestUserInputProvider(
            listOf(
                "L 1 2 6 2",
                "C 20 4",
                "L 1 2 6 2",
                "L 6 3 6 4",
                "R 14 1 18 3",
                "B 10 3 o",
                "q",
                "Q"
            )
        )

        val textOutputReceiver = TestTextOutputReceiver(
            listOf(
                CLI_PROMPT,
                CLI_PROMPT,
                """
                    ----------------------
                    |                    |
                    |                    |
                    |                    |
                    |                    |
                    ----------------------
                
                """.trimIndent(),
                CLI_PROMPT,
                """
                    ----------------------
                    |                    |
                    |xxxxxx              |
                    |                    |
                    |                    |
                    ----------------------

                """.trimIndent(),
                CLI_PROMPT,
                """
                    ----------------------
                    |                    |
                    |xxxxxx              |
                    |     x              |
                    |     x              |
                    ----------------------
                    
                """.trimIndent(),
                CLI_PROMPT,
                """
                    ----------------------
                    |             xxxxx  |
                    |xxxxxx       x   x  |
                    |     x       xxxxx  |
                    |     x              |
                    ----------------------
                    
                """.trimIndent(),
                CLI_PROMPT,
                """
                    ----------------------
                    |oooooooooooooxxxxxoo|
                    |xxxxxxooooooox   xoo|
                    |     xoooooooxxxxxoo|
                    |     xoooooooooooooo|
                    ----------------------
                    
                """.trimIndent(),
                CLI_PROMPT,
                CLI_PROMPT
            )
        )

        val textErrorOutputReceiver = TestTextOutputReceiver(
            listOf(
                "Canvas has to be created first\n",
                "$INVALID_COMMAND_MESSAGE\n"
            )
        )

        val app = CanvasCLIApplication(
            commandParser = CommandParser(),
            userInputProvider = userInputProvider,
            textOutputReceiver = textOutputReceiver,
            textErrorOutputReceiver = textErrorOutputReceiver,
            canvasPresenter = AsciiFramedCanvasPresenter(AsciiCanvasRenderer())
        )

        app.start()
    }
}