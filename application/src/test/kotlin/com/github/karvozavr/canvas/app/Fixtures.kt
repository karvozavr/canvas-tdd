package com.github.karvozavr.canvas.app

import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.PixelData
import com.github.karvozavr.canvas.canvas.PixelValue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

fun defaultCanvasOfSize(width: Int, height: Int, defaultPixelValue: PixelValue = PixelValue('.')): Canvas {
    val canvas = Canvas.ofSize(width = width, height = height, defaultPixelValue = defaultPixelValue)
    canvas.shouldNotBeNull()
    return canvas
}

fun emptyApplicationState() =
    ApplicationState(canvas = null)

fun applicationStateWithCanvas() =
    ApplicationState(canvas = defaultCanvasOfSize(10, 10))

fun canvasFromString(canvas: String): Canvas {
    val lines = canvas.split("\n")
    val width = lines[0].length
    val height = lines.size

    lines.forEach { it.length shouldBe width }

    val pixelData: PixelData = lines.asSequence()
        .flatMap { it.asSequence() }
        .map { PixelValue(it) }
        .toList()
        .toTypedArray()

    val newCanvas = Canvas.ofSize(width, height)
    return newCanvas.draw { setPixelAt, _ ->
        var i = 0
        newCanvas.forEachPixel { point, color ->
            setPixelAt(point, pixelData[i++])
        }
    }
}
