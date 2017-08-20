package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.Preconditions.checkArgument

class LongHand private constructor(val minute: Int) {
    init {

        checkArgument(minute >= 0,
                "minute must not be negative:" + minute)
        checkArgument(minute < 60,
                "minute must less than 60:" + minute)
    }

    fun degree(): Degree {
        return Degree.of(360 / 60 * this.minute)
    }

    companion object {

        fun of(minute: Int): LongHand {
            return LongHand(minute)
        }
    }
}