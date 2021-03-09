package com.github.karvozavr.canvas.command

import com.github.karvozavr.canvas.canvas.*

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
        return canvas.draw { setPixelAt ->
            fillAreaBreadthFirst(canvas, setPixelAt)
        }
    }

    private fun fillAreaBreadthFirst(canvas: Canvas, setPixelAt: SetPixelAt) {
        val color = canvas.pixelAt(initialPoint)
        val processed = mutableSetOf<CanvasPoint>()
        val queue = ArrayDeque<CanvasPoint>()
        queue.addLast(initialPoint)

        while (queue.isNotEmpty()) {
            val point = queue.removeFirst()
            setPixelAt(point, pixelValue)
            processed.add(point)
            addNeighboursOfPointToQueue(point, queue, processed, canvas, color)
        }
    }

    private fun addNeighboursOfPointToQueue(
        point: CanvasPoint,
        queue: ArrayDeque<CanvasPoint>,
        processed: Set<CanvasPoint>,
        canvas: Canvas,
        color: PixelValue
    ) {
        val pointRow = point.row.row
        val pointCol = point.column.column

        val neighbors = listOf(
            pointRow - 1 to pointCol,
            pointRow to pointCol - 1,
            pointRow + 1 to pointCol,
            pointRow to pointCol + 1,
        )

        neighbors
            .mapNotNull { (row, col) -> canvasPointIfValid(row, col, canvas) }
            .filter { canvas.pixelAt(it) == color && !processed.contains(it) }
            .forEach(queue::addLast)
    }

    private fun canvasPointIfValid(row: Int, col: Int, canvas: Canvas): CanvasPoint? =
        if (row > 0 && row <= canvas.height && col > 0 && col <= canvas.width) {
            CanvasPoint(row.row, col.col)
        } else {
            null
        }
}

