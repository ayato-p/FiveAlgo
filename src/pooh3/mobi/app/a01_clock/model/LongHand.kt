package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.elseThrow

class LongHand (val minute: Int) {
    init {
        (minute >= 0).elseThrow("minute must not be negative:" + minute)
        (minute < 60).elseThrow("minute must less than 60:" + minute)
    }
}

// Shortとは違う感じになるが、引数の要不要が明示的になって良いかも？
val LongHand.degree: Degree
    get() = (360 / 60 * this.minute).degree


val Int.longHand: LongHand
    get() = LongHand(this)