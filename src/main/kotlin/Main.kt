@file:JvmName("Main")

import util.VectorLong
import virtualWindow.VirtualWindow

fun main(args: Array<String>) {
    val position = VectorLong(0, 0)
    val size = VectorLong(80, 40)
    val window = VirtualWindow(position, size)
    window.frequency = 10.0
    window.show()

    // TEST CODE //
    /*while(true){
        for (i in virtualWindow.pixels.indices) {
            virtualWindow.pixels[i].colorBackground = Color(RandomManager.randomDouble(), RandomManager.randomDouble(), RandomManager.randomDouble())
            virtualWindow.pixels[i].colorForeground = Color(RandomManager.randomDouble(), RandomManager.randomDouble(), RandomManager.randomDouble())
        }

        Thread.sleep(1000)
    }*/
    //-----------//
}