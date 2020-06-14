package virtualWindow.view

import util.VectorLong
import virtualWindow.Pixel

abstract class View(
        val id: String,
        val position: VectorLong,
        val size: VectorLong
) {
    abstract fun getPrintBuffer(): PrintBuffer
    abstract fun getPixels(): ArrayList<Pixel>
}