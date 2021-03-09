package com.github.karvozavr.canvas

import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.PixelData
import com.github.karvozavr.canvas.canvas.PixelValue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

fun defaultCanvasOfSize(width: Int, height: Int, defaultPixelValue: PixelValue): Canvas {
    val canvas = Canvas.ofSize(width = width, height = height, defaultPixelValue = defaultPixelValue)
    canvas.shouldNotBeNull()
    return canvas
}

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

    return Canvas(width = width, height = height, pixelData = pixelData)
}
