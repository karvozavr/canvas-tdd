package com.github.karvozavr.canvas.app.view

import com.github.karvozavr.canvas.canvas.Canvas

interface CanvasPresenter<out View> {

    fun present(canvas: Canvas): View
}
