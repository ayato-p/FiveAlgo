package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.checkNotNull


class Clock private constructor(shortHand: ShortHand, longHand: LongHand) {
    val shortHand: ShortHand = shortHand.checkNotNull
    val longHand: LongHand = longHand.checkNotNull

    companion object {

        fun build(shortHand: ShortHand, longHand: LongHand): Clock {
            return Clock(shortHand, longHand)
        }
    }
}
