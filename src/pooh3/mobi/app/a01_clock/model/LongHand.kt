package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.orThrow

class LongHand (val minute: Int) {
    init {
        (minute >= 0).orThrow("minute must not be negative:" + minute)
        (minute < 60).orThrow("minute must less than 60:" + minute)
    }
}

fun LongHand.degree(): Degree {
    return Degree(360 / 60 * this.minute)
}

val Int.longHand: LongHand
    get() = LongHand(this)