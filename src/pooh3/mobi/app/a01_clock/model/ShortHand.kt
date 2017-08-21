package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.orThrow

class ShortHand(hour: Int) {
    val hour: Int
    init {
        (hour >= 0).orThrow("hour must not be negative:" + hour)
        (hour < 24).orThrow("hour must less than 24:" + hour)
        this.hour = hour % 12
    }
}

fun ShortHand.degree(longHand: LongHand): Degree {
    return (360 / 12 * this.hour)
            .degree
            .plus(longHand.let {
                if (it.minute == 0)
                    0.degree
                else
                    (30 / (60 / it.minute)).degree
            })
}

val Int.shortHand: ShortHand
    get() = ShortHand(this)