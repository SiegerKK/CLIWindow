package virtualWindow.view

import util.Color
import util.Console
import util.VectorLong

class TextView(
        id: String, position: VectorLong, size: VectorLong,
        var text: String,
        var backgroundColor: Color = Color(0.0,0.0,0.0),
        var foregroundColor: Color = Color(0.95, 0.95, 0.95)/*,
        var marginText: Int = 0*/
) : View(id, position, size) {
    override fun getPrintBuffer(): PrintBuffer {
        val result = StringBuilder()
        val words = text.replace("\n", "").split(" ")
        val cursor = VectorLong(0, 0)

        // Init colors
        result.append("\u001b[48;5;${backgroundColor.asciiCode}m")
        result.append("\u001b[38;5;${foregroundColor.asciiCode}m")

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
}