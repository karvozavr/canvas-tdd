package com.github.karvozavr.canvas.app.io

class StdoutTextOutputReceiver : TextOutputReceiver {
    override fun print(textData: String) {
        kotlin.io.print(textData)
    }
}
