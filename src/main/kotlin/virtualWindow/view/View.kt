package virtualWindow.view

import util.VectorLong

abstract class View(
        val id: String,
        val position: VectorLong,
        val size: VectorLong
) {
    abstract fun getPrintBuffer(): PrintBuffer
}