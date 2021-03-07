package com.github.karvozavr.canvas.renderer

import com.github.karvozavr.canvas.canvas.Canvas

interface CanvasRenderer<T> {

    fun renderCanvas(canvas: Canvas): T
}