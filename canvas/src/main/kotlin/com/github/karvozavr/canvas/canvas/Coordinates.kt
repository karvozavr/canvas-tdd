package com.github.karvozavr.canvas.canvas

inline class Row internal constructor(val row: Int)

val Int.row: Row
    get() = if (this > 0)
        Row(this)
    else
        throw RuntimeException("Row has to be a non-negative integer")

inline class Column internal constructor(val column: Int)

val Int.col: Column
    get() = if (this > 0)
        Column(this)
    else
        throw RuntimeException("Column has to be a non-negative integer")

inline class XCoordinate internal constructor(val x: Int)

val Int.x: XCoordinate
    get() = if (this > 0)
        XCoordinate(this)
    else
        throw RuntimeException("X coordinate has to be a non-negative integer")

inline class YCoordinate internal constructor(val y: Int)

val Int.y: YCoordinate
    get() = if (this > 0)
        YCoordinate(this)
    else
        throw RuntimeException("Y coordinate has to be a non-negative integer")