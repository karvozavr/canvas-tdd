package com.github.karvozavr.canvas.app.view

import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer

class AsciiFramedCanvasPresenter(
    private val asciiCanvasRenderer: AsciiCanvasRenderer
) : CanvasPresenter<CanvasTextView> {

    override fun present(canvas: Canvas): CanvasTextView {
        val asciiCanvas = asciiCanvasRenderer.renderCanvas(canvas)
        val horizontalFrameBorder = "-".repeat(canvas.width + 2)

        val viewBuilder = StringBuilder()
        viewBuilder.append(horizontalFrameBorder)
        viewBuilder.append('\n')

        asciiCanvas.canvasRows.forEach { viewBuilder.append("|$it|\n") }

        viewBuilder.append(horizontalFrameBorder)

        return CanvasTextView(viewBuilder.toString())
    }
}