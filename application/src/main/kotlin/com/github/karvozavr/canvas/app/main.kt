import com.github.karvozavr.canvas.app.CanvasCLIApplication
import com.github.karvozavr.canvas.app.io.CommandParser
import com.github.karvozavr.canvas.app.io.StdinUserInputProvider
import com.github.karvozavr.canvas.app.io.StdoutTextOutputReceiver
import com.github.karvozavr.canvas.app.view.AsciiFramedCanvasPresenter
import com.github.karvozavr.canvas.renderer.AsciiCanvasRenderer

fun main() {
    val application = CanvasCLIApplication(
        commandParser = CommandParser(),
        userInputProvider = StdinUserInputProvider(),
        textOutputReceiver = StdoutTextOutputReceiver(),
        textErrorOutputReceiver = StdoutTextOutputReceiver(),
        canvasPresenter = AsciiFramedCanvasPresenter(AsciiCanvasRenderer())
    )

    application.start()
}
