package pooh3.mobi.app.a01_clock.model


import pooh3.mobi.Preconditions.checkNotNull

class Clock private constructor(shortHand: ShortHand, longHand: LongHand) {
    val shortHand: ShortHand = checkNotNull(shortHand)
    val longHand: LongHand = checkNotNull(longHand)

    override fun toString(): String {
        return "${shortHand.hour}:${longHand.minute}"
    }

    companion object {

        fun build(shortHand: ShortHand, longHand: LongHand): Clock {
            return Clock(shortHand, longHand)
        }
    }
}
