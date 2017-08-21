package pooh3.mobi.app.a01_clock.model

import pooh3.mobi.app.a01_clock.application.GetClockHandsDegree
import sun.jvm.hotspot.utilities.Assert

import java.util.ArrayList

class TestClock {

    private val longHandFixtures = ArrayList<Fixture>()
    private val shortHandFixtures = ArrayList<Fixture>()
    private val shortHandMinFixtures = ArrayList<Fixture>()

    init {
        longHandFixtures.add(Fixture(0, 0))
        longHandFixtures.add(Fixture(15, 90))
        longHandFixtures.add(Fixture(30, 180))
        longHandFixtures.add(Fixture(45, 270))
        //        longHandFixtures.add(new Fixture(60, 360)); // exception

        shortHandFixtures.add(Fixture(0, 0))
        shortHandFixtures.add(Fixture(3, 90))
        shortHandFixtures.add(Fixture(6, 180))
        shortHandFixtures.add(Fixture(9, 270))
        shortHandFixtures.add(Fixture(12, 0))
        shortHandFixtures.add(Fixture(15, 90))
        shortHandFixtures.add(Fixture(18, 180))
        shortHandFixtures.add(Fixture(21, 270))

        shortHandMinFixtures.add(Fixture(0, 0))
        shortHandMinFixtures.add(Fixture(3, 90))
        shortHandMinFixtures.add(Fixture(6, 180))
        shortHandMinFixtures.add(Fixture(9, 90))
        shortHandMinFixtures.add(Fixture(12, 0))
        shortHandMinFixtures.add(Fixture(15, 90))
        shortHandMinFixtures.add(Fixture(18, 180))
        shortHandMinFixtures.add(Fixture(21, 90))
    }

    @Throws(Exception::class)
    fun test() {
        setUp()
        aLongHandTest_minute_to_degree(longHandFixtures)
        aShortHandTest_hour_to_degree(shortHandFixtures)
        aShortHandTest_hour_to_min_degree(shortHandMinFixtures)
        hour10minute10_answer115degree()
    }

    fun setUp() {}

}


val aShortHandTest_hour_to_degree:
        (shortHandFixtures: ArrayList<Fixture>) -> Unit =
        { list ->
            list.forEach { shortHandFixture ->
                shortHandFixture.aTime
                        .shortHand
                        .degree(0.longHand)
                        .value
                        .apply {
                            // side effect
                            println("(hand-degree (short-hand " +
                                    "${shortHandFixture.aTime})) " +
                                    "\n> $this") }
                        .then {
                            Assert.that(this == shortHandFixture.aDegree,
                                    "${shortHandFixture.aTime} " +
                                            "actual:$this  " +
                                            "expected:${shortHandFixture.aDegree}")
                        }
            }
        }

val aShortHandTest_hour_to_min_degree:
        (shortHandMinFixtures: ArrayList<Fixture>) -> Unit =
        { list ->
            list.forEach { shortHandFixture ->
                shortHandFixture.aTime.shortHand
                        .degree(0.longHand)
                        .minusMin(Degree(0))
                        .apply {
                            // side effect
                            println("(degree-min (hand-degree (short-hand " +
                                    "${shortHandFixture.aTime})) " +
                                    "\n> ${this.value}") }
                        .then {
                            Assert.that(this.value == shortHandFixture.aDegree,
                                    "${shortHandFixture.aTime}" +
                                            "actual:${this.value} " +
                                            "expected:${shortHandFixture.aDegree} ")
                        }
            }
        }

val aLongHandTest_minute_to_degree:
        (longHandFixtures : ArrayList<Fixture>) -> Unit =
        { list ->
            list.forEach { fixture ->
                fixture.aTime
                        .longHand
                        .degree()
                        .value
                        .apply {
                            // side effect
                            println("(hand-degree \n" +
                                    "    (long-hand ${fixture.aTime})) " +
                                    "\n> $this") }
                        .then {
                            Assert.that(this == fixture.aDegree,
                                    "${fixture.aTime} " +
                                            "actual:$this " +
                                            "expected:${fixture.aDegree}")
                        }
            }
        }


val hour10minute10_answer115degree:
        () -> Unit =
        {
            "10:10"
                    .let {
                        Pair(it, GetClockHandsDegree(DegreeCalcService()).execute(it)) }
                    .apply {
                        // side effect
                        println("(degree-min (clock-degree (clock-str " +
                                "\"${this.first}\")) " +
                                "\n> ${this.second.value}") }
                    .then {
                        Assert.that(this.second.value == 115,
                                "${this.first} actual:${this.second.value} expected:" + 115)
                    }
        }



class Fixture(val aTime: Int, val aDegree: Int)

private inline fun <T> T.then(block: T.() -> Unit) = block()