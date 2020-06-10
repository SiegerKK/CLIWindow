package util

import kotlin.math.sqrt

data class VectorLong(var x: Long, var y: Long) {
    fun distance(vector: VectorLong): Double = sqrt((x - vector.x)*(x - vector.x).toDouble()) + ((y - vector.y)*(y - vector.y))
    fun lengthOfVector(): Double = sqrt((x - x)*(x - x).toDouble()) + ((y - y)*(y - y))

    operator fun minus(vector: VectorLong): VectorLong = VectorLong(x - vector.x, y - vector.y)
    operator fun plus(vector: VectorLong): VectorLong = VectorLong(x + vector.x, y + vector.y)
    operator fun times(value: Double): VectorLong = VectorLong(x * value.toLong(), y * value.toLong())
    operator fun div(value: Double): VectorLong = VectorLong(x / value.toLong(), y / value.toLong())
}