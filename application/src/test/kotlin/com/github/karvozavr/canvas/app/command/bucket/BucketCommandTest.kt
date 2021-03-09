package com.github.karvozavr.canvas.app.command.bucket

import com.github.karvozavr.canvas.app.ApplicationState
import com.github.karvozavr.canvas.app.canvasFromString
import com.github.karvozavr.canvas.app.defaultCanvasOfSize
import com.github.karvozavr.canvas.canvas.CanvasPoint
import com.github.karvozavr.canvas.canvas.PixelValue
import com.github.karvozavr.canvas.canvas.x
import com.github.karvozavr.canvas.canvas.y
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer
import io.kotest.assertions.arrow.either.shouldBeLeft
import io.kotest.assertions.arrow.either.shouldBeRight
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BucketCommandTest {

    @Test
    fun `should fill area`() {
        val canvas = canvasFromString(
            """
            ......
            .xxxx.
            .x..x.
            .xxxx.
            ......
            ..xx..
        """.trimIndent()
        )
        val command = BucketCommand(initialPoint = CanvasPoint.of(2.x, 5.y), color = PixelValue('o'))

        val result = command.execute(ApplicationState(canvas))
        result.shouldBeRight()

        val newCanvas = result.orNull()?.canvas.shouldNotBeNull()

        val expected = """
            oooooo
            oxxxxo
            ox..xo
            oxxxxo
            oooooo
            ooxxoo
        """.trimIndent()
        AsciiCanvasRenderer().renderCanvas(newCanvas).toString() shouldBe expected
    }

    @Test
    fun `should report error for non-existing canvas`() {
        val appState = ApplicationState(null)
        val command = BucketCommand(initialPoint = CanvasPoint.of(2.x, 5.y), color = PixelValue('o'))

        command.execute(appState).shouldBeLeft(CanvasDoesNotExist)
    }

    @Test
    fun `should report error for point out of canvas`() {
        val canvas = defaultCanvasOfSize(5, 5, PixelValue('.'))
        val appState = ApplicationState(canvas)
        val command = BucketCommand(initialPoint = CanvasPoint.of(42.x, 5.y), color = PixelValue('o'))

        command.execute(appState).shouldBeLeft(PointIsOutOfCanvas)
    }
}
