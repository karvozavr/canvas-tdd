package com.github.karvozavr.canvas.canvas

typealias SetPixelAt = (Row, Column, PixelValue) -> Unit
typealias DrawingFunction = (SetPixelAt) -> Unit
typealias PixelData = Array<PixelValue>

class Canvas internal constructor(
    val width: Int,
    val height: Int,
    private val pixelData: PixelData
) {

    companion object {
        fun ofSize(
            width: Int,
            height: Int,
            defaultPixelValue: PixelValue = PixelValue(' ')
        ): Canvas {
            val pixelData = Array(height * width) { defaultPixelValue }

            return if (width > 0 && height > 0)
                Canvas(width, height, pixelData)
            else
                throw RuntimeException("Width and height of the canvas should be positive integers")
        }
    }

    fun pixelAt(row: Row, col: Column): PixelValue {
        return pixelData.getPixelValue(row, col)
    }

    fun forEachPixel(func: (row: Row, col: Column, value: PixelValue) -> Unit) {
        for (rowIdx in 1..height) {
            for (colIdx in 1..width) {
                func(rowIdx.row, colIdx.col, pixelData.getPixelValue(rowIdx.row, colIdx.col))
            }
        }
    }

    fun draw(drawingFunction: DrawingFunction): Canvas {
        val newPixelData = pixelData.copyOf()

        val setPixelAt = { row: Row, col: Column, value: PixelValue ->
            newPixelData.setPixelValue(row, col, value)
        }

        drawingFunction(setPixelAt)

        return Canvas(width = width, height = height, pixelData = newPixelData)
    }

    private fun PixelData.getPixelValue(row: Row, col: Column): PixelValue {
        validateRowAndColumn(row, col)
        return this[(row.row - 1) * width + col.column - 1]
    }

    private fun PixelData.setPixelValue(row: Row, col: Column, value: PixelValue) {
        validateRowAndColumn(row, col)
        this[(row.row - 1) * width + col.column - 1] = value
    }

    private fun validateRowAndColumn(row: Row, col: Column) {
        val rowIsValid = row.row in 1..height
        if (!rowIsValid) {
            throw RuntimeException("Row values should be in range 1..height")
        }

        val columnIsValid = col.column in 1..width
        if (!columnIsValid) {
            throw RuntimeException("Column values should be in range 1..width")
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