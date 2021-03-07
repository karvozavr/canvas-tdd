package com.github.karvozavr.canvas.canvas

class Canvas internal constructor(val width: Int, val height: Int) {

    companion object {
        fun ofSize(width: Int, height: Int): Canvas? =
            if (width > 0 && height > 0) Canvas(width, height) else null
    }
}
