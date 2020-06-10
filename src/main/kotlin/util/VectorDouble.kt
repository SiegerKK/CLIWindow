package util

import kotlin.math.sqrt

data class VectorDouble(var x: Double, var y: Double) {
    fun distance(vector: VectorDouble): Double = sqrt((x - vector.x)*(x - vector.x)) + ((y - vector.y)*(y - vector.y))
    fun lengthOfVector(): Double = sqrt((x - x)*(x - x)) + ((y - y)*(y - y))

    operator fun minus(vector: VectorDouble): VectorDouble = VectorDouble(x - vector.x, y - vector.y)
    operator fun plus(vector: VectorDouble): VectorDouble = VectorDouble(x + vector.x, y + vector.y)
    operator fun times(value: Double): VectorDouble = VectorDouble(x * value, y * value)
    operator fun div(value: Double): VectorDouble = VectorDouble(x / value, y / value)
}