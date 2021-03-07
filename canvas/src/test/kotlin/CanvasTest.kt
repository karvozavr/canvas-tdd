import com.github.karvozavr.canvas.canvas.Canvas
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CanvasTest {

    @Test
    fun `should create empty canvas 20x10`() {
        val canvas = Canvas.ofSize(width=20, height=10)
        canvas.shouldNotBeNull()

        canvas.width shouldBe 20
        canvas.height shouldBe 10
    }

    @Test
    fun `should not create canvas of invalid size`() {
        Canvas.ofSize(width = 0, height = 10).shouldBeNull()
        Canvas.ofSize(width = 1, height = -1).shouldBeNull()
    }
}