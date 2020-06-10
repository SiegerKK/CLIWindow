package virtualWindow

import util.VectorLong

interface View {
    fun getId(): Long
    fun getPosition(): VectorLong
    fun getSize(): VectorLong
    fun getPrintBuffer(): PrintBuffer
}