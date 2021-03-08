package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.Canvas

fun interface DrawingCommand {

    fun draw(canvas: Canvas): Canvas
}
