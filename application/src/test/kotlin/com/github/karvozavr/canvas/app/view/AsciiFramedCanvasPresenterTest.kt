package com.github.karvozavr.canvas.app.view

import com.github.karvozavr.canvas.app.defaultCanvasOfSize
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class AsciiFramedCanvasPresenterTest {

    @Test
    fun `should draw canvas in frame`() {
        val presenter = AsciiFramedCanvasPresenter(asciiCanvasRenderer = AsciiCanvasRenderer())

        val textView = presenter.present(defaultCanvasOfSize(5, 3, PixelValue('.')))

        textView.text shouldBe """
            -------
            |.....|
            |.....|
            |.....|
            -------
        """.trimIndent()
    }
}
