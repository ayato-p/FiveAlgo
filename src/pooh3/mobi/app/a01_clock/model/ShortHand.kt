package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.Preconditions.checkArgument
import pooh3.mobi.Preconditions.checkNotNull

class ShortHand private constructor(hour: Int) {
    val hour: Int

    init {

        checkArgument(hour >= 0,
                "hour must not be negative:" + hour)
        checkArgument(hour < 24,
                "hour must less than 24:" + hour)

        this.hour = hour % 12
    }

    fun degree(longHand: LongHand): Degree {
        return Degree.of(360 / 12 * this.hour + additionalDegree(checkNotNull(longHand).minute))
    }

    private fun additionalDegree(minute: Int): Int {
        return if (minute == 0) 0 else 30 / (60 / minute)
    }

    companion object {

        fun of(hour: Int): ShortHand {
            return ShortHand(hour)
        }
    }
}