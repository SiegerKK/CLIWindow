@file:JvmName("Main")

import util.Color
import util.VectorLong
import virtualWindow.VirtualWindow
import virtualWindow.view.TextView

fun main(args: Array<String>) {
    val position = VectorLong(0, 0)
    val size = VectorLong(80, 40)
    val window = VirtualWindow(position, size)
    window.frequency = 10.0
    window.show()

    Thread.sleep(1000)

    val tvTest = (TextView(
            "tvTest",
            VectorLong(5, 2),
            VectorLong(60, 30),
            "Пара мыслей про Михаила Ефремова, культуру отмены и уничтожение памятников, которые по какой-то причине недостаточно проговаривают. \n" +
                    "\n" +
                    "Если в отношении большинства действует произвол, а в отношении кого-то одного вдруг срабатывает закон - это не повод радоваться тому, что хоть где-то закон работает. Такое положение дел называется номенклатурная привилегия и к законности не имеет никакого отношения.\n" +
                    "\n" +
                    "Сегодня Михаилу Ефремову помогает то же телефонное право, которое до этого вступалось за Васильеву и постоянно вступается за полицейских. Это не значит, что нужно желать Михаилу Ефремову жестокого наказания или требовать расправы. Но и радоваться тому, что на фоне уголовных дел за твит, привилегированного алкаша отправляют провести время с близкими перед приговором, не стоит. Убитому безразлично, кто его сбил. Значит и закону должно быть безразлично, насколько великим актёром был убийца. Мне тошно жить в стране, где в отношении Синицы и Ефремова закон работает настолько по-разному.\n" +
                    "\n" +
                    "И ещё одна мысль, которую в свете уничтожения памятников и культуры отмены проговорить необходимо: Михаил Ефремов - великий актёр. Его фильмы не стали хуже от того что он пьяным убил человека. Его талант от этого не стал меньше. Роли не стали менее выразительными. Он всё ещё наше национальное достояние. Один из немногих людей массовой культуры, который не боялся идти против путинского режима (хотя и сладко подбухивал с его апологетами, за что его стоит презирать).\n" +
                    "\n" +
                    "В этом драма погромов, захлестнувших Европу и США. Потерявшие человеческий облик вандалы сносят памятники лучшим из нас. Не святым, не выдуманным супергероям. А людям, благодаря которым мы вышли из той тьмы, которой сегодня их пытаются замазать. И которую вандалы несут в современность, водружая на свои флаги тиранов, прославившихся только своей кровожадностью. Что не так на картине?",
            Color(0.0, 0.0, 0.8),
            Color(0.0, 1.0, 0.0)
    ))
    window.addView(tvTest)

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