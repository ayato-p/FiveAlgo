package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.orThrow
import pooh3.mobi.checkNotNull

object ClockFactory {

    private val DIV = ":"
    private val HOUR_INDEX = 0
    private val MINUTE_INDEX = 1

    fun createByStr(hourAndMinute: String): Clock {
        hourAndMinute
                .checkNotNull("hourAndMinute require not null.")
                .split(DIV).dropLastWhile(String::isEmpty).toTypedArray()
                .let {
                    (it.size == 2).orThrow(
                            "check format:$hourAndMinute  \n eg. \"10:10\"")

                    return Clock.build(
                            it[HOUR_INDEX].parseInt.shortHand,
                            it[MINUTE_INDEX].parseInt.longHand
                    )
                }
    }
}

val String.parseInt: Int
    get() = Integer.parseInt(this)
