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
        aLongHandTest_minute_to_degree()
        aShortHandTest_hour_to_degree()
        aShortHandTest_hour_to_min_degree()
        hour10minute10_answer115degree()
    }

    fun setUp() {}

    @Throws(Exception::class)
    fun aLongHandTest_minute_to_degree() {
        longHandFixtures.forEach { longHandFixture ->
            LongHand.of(longHandFixture.aTime)
                    .degree()
                    .value
                    .let {
                        println("(hand-degree (long-hand ${longHandFixture.aTime})) " +
                                "-> $it == ${longHandFixture.aDegree}")

                        Assert.that(it == longHandFixture.aDegree,
                                "${longHandFixture.aTime} " +
                                        "actual:$it " +
                                        "expected:${longHandFixture.aDegree}")
                    }
        }
    }

    @Throws(Exception::class)
    fun aShortHandTest_hour_to_degree() {
        shortHandFixtures.forEach{shortHandFixture ->
            ShortHand.of(shortHandFixture.aTime)
                    .degree(LongHand.of(0))
                    .value
                    .let {
                        println("(hand-degree (short-hand " +
                                "${shortHandFixture.aTime})) " +
                                "-> $it == ${shortHandFixture.aDegree}")

                        Assert.that(it == shortHandFixture.aDegree,
                                "${shortHandFixture.aTime} " +
                                        "actual:$it  " +
                                        "expected:${shortHandFixture.aDegree}")
                    }
        }
    }

    @Throws(Exception::class)
    fun aShortHandTest_hour_to_min_degree() {
        shortHandMinFixtures.forEach { shortHandFixture ->
            ShortHand.of(shortHandFixture.aTime)
                    .degree(LongHand.of(0))
                    .minusMin(Degree.of(0))
                    .let {
                        println(
                                "(degree-min (hand-degree (short-hand " +
                                        "${shortHandFixture.aTime})) -> ${it.value}"
                                        + " == ${shortHandFixture.aDegree}")

                        Assert.that(it.value == shortHandFixture.aDegree,
                                "${shortHandFixture.aTime}" +
                                        "actual:${it.value } " +
                                        "expected:${shortHandFixture.aDegree} ")
                    }
        }
    }

    @Throws(Exception::class)
    fun hour10minute10_answer115degree() {
        val c = "10:10"
        GetClockHandsDegree(DegreeCalcService()).execute(c).let {
            println("(degree-min (clock-degree (clock-str \"$c\")) -> ${it.value} == 115")

            Assert.that(it.value == 115,
                    "$c actual:${it.value} expected:" + 115)
        }
    }

    internal class Fixture(val aTime: Int, val aDegree: Int)
}
