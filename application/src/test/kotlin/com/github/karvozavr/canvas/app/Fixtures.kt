package com.github.karvozavr.canvas.app

import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.PixelValue
import io.kotest.matchers.nulls.shouldNotBeNull

fun defaultCanvasOfSize(width: Int, height: Int, defaultPixelValue: PixelValue = PixelValue('.')): Canvas {
    val canvas = Canvas.ofSize(width = width, height = height, defaultPixelValue = defaultPixelValue)
    canvas.shouldNotBeNull()
    return canvas
}

fun emptyApplicationState() =
    ApplicationState(canvas = null)

fun applicationStateWithCanvas() =
    ApplicationState(canvas = defaultCanvasOfSize(10, 10))