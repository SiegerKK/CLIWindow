package util

import java.util.*

object RandomManager {
    private val random = Random()
    
    fun randomInt(): Int {
        return random.nextInt()
    }
    fun randomInt(range: Int): Int {
        return random.nextInt(range)
    }
    fun randomInt(min: Int, max: Int): Int {
        return random.nextInt(max + 1 - min) + min
    }

    fun randomDouble(): Double {
        return random.nextDouble()
    }
}