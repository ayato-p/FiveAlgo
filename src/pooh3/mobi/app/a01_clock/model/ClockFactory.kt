package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.Preconditions.checkArgument
import pooh3.mobi.Preconditions.checkNotNull

object ClockFactory {

    private val DIV = ":"
    private val HOUR_INDEX = 0
    private val MINUTE_INDEX = 1

    fun createByStr(hourAndMinute: String): Clock {
        checkNotNull(hourAndMinute,
                "hourAndMinute require not null.")
                .split(DIV).dropLastWhile(String::isEmpty).toTypedArray()
                .let{
                    checkArgument(it.size == 2,
                            "check format:$hourAndMinute  \n eg. \"10:10\"")

                    return Clock.build(
                            ShortHand.of(Integer.parseInt(it[HOUR_INDEX])),
                            LongHand.of(Integer.parseInt(it[MINUTE_INDEX]))
                    )
                }
    }
}
