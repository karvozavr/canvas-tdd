package com.github.karvozavr.canvas

import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.CanvasPoint

fun isPointOnCanvas(point: CanvasPoint, canvas: Canvas) =
    point.row.row <= canvas.height && point.column.column <= canvas.width
