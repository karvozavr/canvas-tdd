package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.Canvas
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.canvas.SetPixelAt
import com.github.karvozavr.canvas.canvas.GetPixelAt
import com.github.karvozavr.canvas.canvas.row
import com.github.karvozavr.canvas.canvas.col

class FillAreaCommand internal constructor(
    val initialPoint: CanvasPoint,
    val pixelValue: PixelValue
) : DrawingCommand {

    companion object {

        fun fillArea(initialPoint: CanvasPoint, pixelValue: PixelValue): FillAreaCommand {
            return FillAreaCommand(initialPoint = initialPoint, pixelValue = pixelValue)
        }
    }

    override fun draw(canvas: Canvas): Canvas {
        return canvas.draw { setPixelAt, getPixelAt ->
            fillAreaBreadthFirst(canvas, setPixelAt, getPixelAt)
        }
    }

    private fun fillAreaBreadthFirst(canvas: Canvas, setPixelAt: SetPixelAt, getPixelAt: GetPixelAt) {
        val color = canvas.pixelAt(initialPoint)
        val processed = mutableSetOf<CanvasPoint>()
        val queue = ArrayDeque<CanvasPoint>()
        queue.addLast(initialPoint)

        while (queue.isNotEmpty()) {
            val point = queue.removeFirst()
            setPixelAt(point, pixelValue)
            addNeighboursOfPointToQueue(point, queue, processed, canvas, color, getPixelAt)
        }
    }

    private fun addNeighboursOfPointToQueue(
        point: CanvasPoint,
        queue: ArrayDeque<CanvasPoint>,
        processed: MutableSet<CanvasPoint>,
        canvas: Canvas,
        color: PixelValue,
        getPixelAt: GetPixelAt
    ) {
        val pointRow = point.row.row
        val pointCol = point.column.column

        val neighbors = listOf(
            pointRow - 1 to pointCol,
            pointRow to pointCol - 1,
            pointRow + 1 to pointCol,
            pointRow to pointCol + 1,
        )

        neighbors.asSequence()
            .mapNotNull { (row, col) -> canvasPointIfValid(row, col, canvas) }
            .filter { getPixelAt(it) == color && !processed.contains(it) }
            .onEach { processed.add(it) }
            .forEach(queue::addLast)
    }

    private fun canvasPointIfValid(row: Int, col: Int, canvas: Canvas): CanvasPoint? {
        val rowIsValid = row > 0 && row <= canvas.height
        val columnIsValid = col > 0 && col <= canvas.width
        return if (rowIsValid && columnIsValid) {
            CanvasPoint(row.row, col.col)
        } else {
            null
        }
    }
}
