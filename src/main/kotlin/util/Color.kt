package util

class Color @JvmOverloads constructor(
        var r: Double = 0.0,
        var g: Double = 0.0,
        var b: Double = 0.0
) {
    companion object {
        private const val MAX_VALUE = 5

        const val FOREGROUND_COLOR = "\u001B[38;5"
        const val BACKGROUND_COLOR = "\u001B[48;5"
        const val CLOSE_COLOR_SYMBOL = "m"

        const val COLOR_DEFAULT = "\u001b[39m"
        const val COLOR_BLACK = "\u001b[30m;0m"
        const val COLOR_RED = "\u001b[31m;0m"
        const val COLOR_GREEN = "\u001b[32m;0m"
        const val COLOR_YELLOW = "\u001b[33m;0m"
        const val COLOR_BLUE = "\u001b[34m;0m"
        const val COLOR_MAGENTA = "\u001b[35m;0m"
        const val COLOR_CYAN = "\u001b[36m;0m"
        const val COLOR_WHITE = "\u001b[37m;0m"
        const val COLOR_BLACK_BRIGHT = "\u001b[30;1m"
        const val COLOR_RED_BRIGHT = "\u001b[31;1m"
        const val COLOR_GREEN_BRIGHT = "\u001b[32;1m"
        const val COLOR_YELLOW_BRIGHT = "\u001b[33;1m"
        const val COLOR_BLUE_BRIGHT = "\u001b[34;1m"
        const val COLOR_MAGENTA_BRIGHT = "\u001b[35;1m"
        const val COLOR_CYAN_BRIGHT = "\u001b[36;1m"
        const val COLOR_WHITE_BRIGHT = "\u001b[37;1m"

        fun getAsciiCode(r: Double, g: Double, b: Double): String {
            val code = (r * MAX_VALUE + 0.5).toInt() * 36 + (g * MAX_VALUE + 0.5).toInt() * 6 + (b * MAX_VALUE + 0.5).toInt() + 16
            return code.toString()
        }
    }

    constructor(color: Color) : this(color.r, color.g, color.b)

    val asciiCode: String
        get() = getAsciiCode(r, g, b)

    fun equals(color: Color): Boolean {
        return asciiCode == color.asciiCode
    }
}