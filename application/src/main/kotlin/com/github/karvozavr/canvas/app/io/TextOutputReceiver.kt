package com.github.karvozavr.canvas.app.io

interface TextOutputReceiver {

    fun print(textData: String)

    fun println(textData: String) {
        print(textData + "\n")
    }
}