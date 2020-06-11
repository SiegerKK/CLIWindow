package util

object Console {
    fun moveCursorUp(n: Long) {
        if(n <= 0) {
            if(n < 0)
                println("moveCursorUp(Long): Wrong input $n")
            return
        }
        print(cursorUp(n))
    }
    fun moveCursorDown(n: Long) {
        if(n <= 0) {
            if(n < 0)
                println("moveCursorDown(Long): Wrong input $n")
            return
        }
        print(cursorDown(n))
    }
    fun moveCursorRight(n: Long) {
        if(n <= 0) {
            if(n < 0)
                println("moveCursorRight(Long): Wrong input $n")
            return
        }
        print(cursorRight(n))
    }
    fun moveCursorLeft(n: Long) {
        if(n <= 0) {
            if(n < 0)
                println("moveCursorLeft(Long): Wrong input $n")
            return
        }
        print(cursorLeft(n))
    }

    fun cursorUp(n: Long): String = "\u001b[" + n + "A"
    fun cursorDown(n: Long): String = "\u001b[" + n + "B"
    fun cursorRight(n: Long): String = "\u001b[" + n + "C"
    fun cursorLeft(n: Long): String = "\u001b[" + n + "D"
}