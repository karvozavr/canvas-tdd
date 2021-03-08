package com.github.karvozavr.canvas.canvas

typealias SetPixelAt = (CanvasPoint, PixelValue) -> Unit
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

    fun pixelAt(canvasPoint: CanvasPoint): PixelValue {
        return pixelData.getPixelValue(canvasPoint)
    }

    fun forEachPixel(func: (CanvasPoint, PixelValue) -> Unit) {
        for (rowIdx in 1..height) {
            for (colIdx in 1..width) {
                val point = CanvasPoint.of(rowIdx.row, colIdx.col)
                func(point, pixelData.getPixelValue(point))
            }
        }
    }

    fun draw(drawingFunction: DrawingFunction): Canvas {
        val newPixelData = pixelData.copyOf()

        val setPixelAt = { canvasPoint: CanvasPoint, value: PixelValue ->
            newPixelData.setPixelValue(canvasPoint, value)
        }

        drawingFunction(setPixelAt)

        return Canvas(width = width, height = height, pixelData = newPixelData)
    }

    private fun PixelData.getPixelValue(canvasPoint: CanvasPoint): PixelValue {
        validatePoint(canvasPoint)
        return this[(canvasPoint.row.row - 1) * width + canvasPoint.column.column - 1]
    }

    private fun PixelData.setPixelValue(canvasPoint: CanvasPoint, value: PixelValue) {
        validatePoint(canvasPoint)
        this[(canvasPoint.row.row - 1) * width + canvasPoint.column.column - 1] = value
    }

    private fun validatePoint(canvasPoint: CanvasPoint) {
        val rowIsValid = canvasPoint.row.row in 1..height
        if (!rowIsValid) {
            throw RuntimeException("Row values should be in range 1..height")
        }

        val columnIsValid = canvasPoint.column.column in 1..width
        if (!columnIsValid) {
            throw RuntimeException("Column values should be in range 1..width")
        }
    }
}
