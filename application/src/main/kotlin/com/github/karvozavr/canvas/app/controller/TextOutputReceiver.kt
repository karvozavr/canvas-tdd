package com.github.karvozavr.canvas.app.controller

interface TextOutputReceiver {

    fun print(textData: String)

    fun println(textData: String) {
        print(textData + "\n")
    }
}