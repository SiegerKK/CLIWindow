package virtualWindow

import util.Color
import util.VectorLong

data class Pixel(
        var position: VectorLong,
        var value: Char,
        var colorForeground: Color = Color(),
        var colorBackground: Color
){
    fun getInfo(): String{
        val result = StringBuilder("Pixel info:\n")
        result.append("Position: ${position.x}\t${position.y}\n")
        result.append("Value: $value\n")
        result.append("Background color: ${colorBackground.asciiCode}\n")
        result.append("Foreground color: ${colorForeground.asciiCode}\n")

        return result.toString()
    }

    fun setValue(pixel: Pixel) {
//        position = pixel.position
        value = pixel.value
        colorForeground = pixel.colorForeground
        colorBackground = pixel.colorBackground
    }
}