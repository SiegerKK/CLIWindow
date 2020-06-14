package virtualWindow.view

import util.Color
import util.Console
import util.VectorLong
import virtualWindow.Pixel

class TextView(
        id: String, position: VectorLong, size: VectorLong,
        var text: String,
        var colorBackground: Color = Color(0.0,0.0,0.0),
        var colorForeground: Color = Color(0.95, 0.95, 0.95),
        var padding: Int = 0
) : View(id, position, size) {
    @Deprecated("For VirtualWindow use getPixels()")
    override fun getPrintBuffer(): PrintBuffer {
        val result = StringBuilder()
        val words = text.replace("\n", "").split(" ")
        val cursor = VectorLong(0, 0)

        // Init colors
        result.append("\u001b[48;5;${colorBackground.asciiCode}m")
        result.append("\u001b[38;5;${colorForeground.asciiCode}m")

        for (word in words){
            // Check borders
            if(cursor.x + word.length > size.x) {
                for(i in 1..size.x-cursor.x) {
                    result.append(" ")
                    cursor.x++
                }
                result.append(Console.cursorLeft(cursor.x))
                cursor.x = 0
                result.append(Console.cursorDown(1))
                cursor.y++
            }
            if(cursor.y >= size.y)
                break

            // Add word
            result.append("$word ")
            cursor.x += word.length + 1
        }

        // Reset cursor position
        result.append(Console.cursorUp(cursor.y))
        result.append(Console.cursorLeft(cursor.x))

        return PrintBuffer(result.toString(), size)
    }

    override fun getPixels(): ArrayList<Pixel> {
        val pixels = ArrayList<Pixel>()
        var textPointer = 0

        for (i in 0 until size.y){
            for (j in 0 until size.x){
                // Init Pixel
                val positionPixel = VectorLong(j, i)
                var value: Char = ' '
                // TODO: refactor checking new line
                // TODO: wrapping words
                if(i >= padding && i < size.y - padding && textPointer < text.length) {
                    if(text[textPointer] == '\n')
                        value = ' '
                    else
                        value = text[textPointer]
                    textPointer++
                }

                // Add Pixel to list
                pixels.add(Pixel(
                        positionPixel,
                        value,
                        colorForeground,
                        colorBackground
                ))
            }
        }

        return pixels
    }
}