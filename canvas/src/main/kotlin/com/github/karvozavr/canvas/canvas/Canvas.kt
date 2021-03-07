package com.github.karvozavr.canvas.canvas

class Canvas internal constructor(val width: Int, val height: Int, private val pixelData: Array<Array<PixelValue>>) {

    fun pixelAt(row: Row, col: Column): PixelValue {
        val rowIsValid = row.row in 1..height
        if (!rowIsValid) {
            throw RuntimeException("Row values should be in range 1..height")
        }

        val columnIsValid = col.column in 1..width
        if (!columnIsValid) {
            throw RuntimeException("Column values should be in range 1..width")
        }

        return pixelData[row.row - 1][col.column - 1]
    }

    companion object {
        fun ofSize(
            width: Int,
            height: Int,
            defaultPixelValue: PixelValue = PixelValue(' ')
        ): Canvas {
            val pixelData = Array(height) { Array(width) { defaultPixelValue } }

            return if (width > 0 && height > 0)
                Canvas(width, height, pixelData)
            else
                throw RuntimeException("Width and height of the canvas should be positive integers")
        }
    }
}

inline class Row internal constructor(val row: Int)

val Int.row: Row
    get() = if (this >= 0)
        Row(this)
    else
        throw RuntimeException("Row has to be a non-negative integer")

inline class Column internal constructor(val column: Int)

val Int.col: Column
    get() = if (this >= 0)
        Column(this)
    else
        throw RuntimeException("Column has to be a non-negative integer")