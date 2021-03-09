package com.github.karvozavr.canvas.app.io

class StdinUserInputProvider : UserInputProvider {
    override fun getUserInput(): String? {
        return readLine()
    }
}
