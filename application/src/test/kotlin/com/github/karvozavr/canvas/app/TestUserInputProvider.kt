package com.github.karvozavr.canvas.app

import com.github.karvozavr.canvas.app.controller.UserInputProvider

class TestUserInputProvider(private val inputs: List<String>) : UserInputProvider {

    private var idx = 0

    override fun getUserInput(): String? {
        return inputs.getOrNull(idx++)
    }
}