package virtualWindow

import util.Color
import util.Console.moveCursorDown
import util.Console.moveCursorLeft
import util.Console.moveCursorRight
import util.Console.moveCursorUp
import util.VectorLong
import virtualWindow.view.View
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
    private var backgroundColor = Color(0.0, 0.0, 0.0)
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
                val time = System.currentTimeMillis()
                moveCursor(position)
                printWindow()
//                println((System.currentTimeMillis() - time) / 1000.0)

                Thread.sleep((1.0 / frequency * 1000).toLong())
            }
        }
    }
    private fun generateScreen(){
        pixels = ArrayList(background)

        // Integrate views' Pixels to VirtualWindow
        for (view: View in views){
            val pixelsView = view.getPixels()
            for (pixel in pixelsView){
                // TODO: not safe if view is out of VirtualWindow
                getPixel(pixel.position + view.position).setValue(pixel)
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
        /*for(view in views){
            val printBuffer = view.getPrintBuffer()
            moveCursor(view.position)

            // Print view
            print(printBuffer.buffer)
        }*/
        moveCursor(VectorLong(0, 0))

        // Reset terminal colors
        foregroundColor = Color(0.95, 0.95, 0.95)
        backgroundColor = Color()
        print("\u001b[48;5;${backgroundColor.asciiCode}m")
        print("\u001b[38;5;${foregroundColor.asciiCode}m")
    }

    fun addView(view: View){
        views.add(view)
    }
    fun deleteView(view: View): Boolean{
        return deleteView(view.id)
    }
    fun deleteView(id: String): Boolean{
        var result = false
        var viewForDeleting: View? = null

        for (viewTemp in views)
            if(id == viewTemp.id) {
                viewForDeleting = viewTemp
                result = true
            }

        if(result)
            views.remove(viewForDeleting)

        return result
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
}