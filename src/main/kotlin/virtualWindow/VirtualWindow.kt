package virtualWindow

import util.Color
import util.VectorLong
import kotlin.concurrent.thread

class VirtualWindow(
        var position: VectorLong,
        var size: VectorLong
) {
    var frequency = 1.0
    var background = ArrayList<Pixel>()

    private var pixels = ArrayList<Pixel>()
    private var views = ArrayList<View>()

    private var cursorPosition = VectorLong(0, 0)
    private var backgroundColor = Color()
    private var foregroundColor = Color(0.95, 0.95, 0.95)

    init{
        // Init pixels on screen
        for(i in 0..size.y - 1)
            for(j in 0..size.x - 1)
                background.add(Pixel(
                        VectorLong(x = j, y = i),
                        ' ',
                        Color(0.95, 0.95, 0.95),
                        Color(0.8, 0.0, 1.0)
                        // TEST CODE //
//                        Color(RandomManager.randomDouble(), RandomManager.randomDouble(), RandomManager.randomDouble()),
//                        Color(RandomManager.randomDouble(), RandomManager.randomDouble(), RandomManager.randomDouble())
                        //-----------//
                ))
    }

    fun endPosition(): VectorLong = position + size

    fun show(){
        thread(start = true) {
            // Init window
            for (i in 0..size.y - 1)
                println()
            moveCursorUp(size.y)

            while (true) {
                var time = System.currentTimeMillis()
                moveCursor(position)
                printWindow()
                println((System.currentTimeMillis() - time) / 1000.0)

                Thread.sleep((1.0 / frequency * 1000).toLong())
            }
        }
    }
    private fun printWindow(){
        // Make picture
        generateScreen()

        // Print pixels
        for (i in 0..size.y - 1){
            for (j in 0..size.x - 1){
                val pixel = pixels[(i * size.x + j).toInt()]
                moveCursor(pixel.position)

                // Optimization
                if(!backgroundColor.equals(pixel.colorBackground)){
                    backgroundColor = pixel.colorBackground
                    print("\u001b[48;5;${backgroundColor.asciiCode}m")
                }
                if(!foregroundColor.equals(pixel.colorForeground)){
                    foregroundColor = pixel.colorForeground
                    print("\u001b[38;5;${foregroundColor.asciiCode}m")
                }
                // No optimization
//                print("\u001b[38;5;${pixel.colorForeground.asciiCode}m")
//                print("\u001b[48;5;${pixel.colorBackground.asciiCode}m")
                print(pixel.value)
                cursorPosition.x++
            }
        }

        // Reset terminal colors
        foregroundColor = Color(0.95, 0.95, 0.95)
        backgroundColor = Color()
        print("\u001b[48;5;${backgroundColor.asciiCode}m")
        print("\u001b[38;5;${foregroundColor.asciiCode}m")
    }

    private fun generateScreen(){
        pixels = ArrayList(background)
        for(view in views){
            val printBuffer = view.getPrintBuffer()
            moveCursor(view.getPosition())
            print(printBuffer.buffer)
        }
    }

    fun getPixel(position: VectorLong): Pixel = getPixel(position.x, position.y)
    fun getPixel(x: Long, y: Long): Pixel = pixels[(y * size.x + x).toInt()]

    private fun moveCursor(positionInput: VectorLong){
        val position = this.position + positionInput
        if (cursorPosition.x > position.x) moveCursorLeft(cursorPosition.x - position.x)
        else if (cursorPosition.x < position.x) moveCursorRight(position.x - cursorPosition.x)
        if (cursorPosition.y > position.y) moveCursorUp(cursorPosition.y - position.y)
        else if (cursorPosition.y < position.y) moveCursorDown(position.y - cursorPosition.y)
        cursorPosition.x += position.x - cursorPosition.x
        cursorPosition.y += position.y - cursorPosition.y
    }
    private fun moveCursorUp(n: Long) {
        if(n <= 0) {
            println("moveCursorUp(Long): Wrong input $n")
            return
        }
        print("\u001b[" + n + "A")
    }
    private fun moveCursorDown(n: Long) {
        if(n <= 0) {
            println("moveCursorDown(Long): Wrong input $n")
            return
        }
        print("\u001b[" + n + "B")
    }
    private fun moveCursorRight(n: Long) {
        if(n <= 0) {
            println("moveCursorRight(Long): Wrong input $n")
            return
        }
        print("\u001b[" + n + "C")
    }
    private fun moveCursorLeft(n: Long) {
        if(n <= 0) {
            println("moveCursorLeft(Long): Wrong input $n")
            return
        }
        print("\u001b[" + n + "D")
    }

}