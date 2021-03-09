package com.github.karvozavr.canvas.app.io

class StderrTextOutputReceiver : TextOutputReceiver {
    override fun print(textData: String) {
        System.err.println(textData)
    }
}