package com.github.karvozavr.canvas.canvas

data class CanvasPoint internal constructor(val row: Row, val column: Column) {

    companion object {
        fun of(row: Row, column: Column): CanvasPoint {
            return CanvasPoint(row, column)
        }

        fun of(x: XCoordinate, y: YCoordinate): CanvasPoint {
            return CanvasPoint(y.y.row, x.x.col)
        }
    }
}
