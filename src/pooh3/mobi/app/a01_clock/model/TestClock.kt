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
                shortHandFixture.aTime.shortHand
                        .degree(0.longHand).value
                        .also { degree ->
                            // side effect
                            println("(hand-degree (short-hand " +
                                    "${shortHandFixture.aTime})) " +
                                    "\n> $degree") }
                        .then { degree ->
                            Assert.that(degree == shortHandFixture.aDegree,
                                    "${shortHandFixture.aTime} " +
                                            "actual:$degree  " +
                                            "expected:${shortHandFixture.aDegree}")
                        }
            }
        }

val aShortHandTest_hour_to_min_degree:
        (shortHandMinFixtures: ArrayList<Fixture>) -> Unit =
        { list ->
            list.forEach { shortHandFixture ->
                shortHandFixture.aTime.shortHand
                        .degree(0.longHand).minusMin(0.degree)
                        .also { degree ->
                            // side effect
                            println("(degree-min (hand-degree (short-hand " +
                                    "${shortHandFixture.aTime})) " +
                                    "\n> ${degree.value}") }
                        .then { degree ->
                            Assert.that(degree.value == shortHandFixture.aDegree,
                                    "${shortHandFixture.aTime}" +
                                            "actual:${degree.value} " +
                                            "expected:${shortHandFixture.aDegree} ")
                        }
            }
        }

val aLongHandTest_minute_to_degree:
        (longHandFixtures : ArrayList<Fixture>) -> Unit =
        { list ->
            list.forEach { fixture ->
                fixture.aTime.longHand
                        .degree().value
                        .also {
                            // side effect
                            println("(hand-degree \n" +
                                    "    (long-hand ${fixture.aTime})) " +
                                    "\n> $it") }
                        .then { degree ->
                            Assert.that(degree == fixture.aDegree,
                                    "${fixture.aTime} " +
                                            "actual:$degree " +
                                            "expected:${fixture.aDegree}")
                        }
            }
        }


val hour10minute10_answer115degree:
        () -> Unit =
        {
            "10:10"
                    .let {
                        it to GetClockHandsDegree(DegreeCalcService()).execute(it) }
                    .also { (clockStr, degree) ->
                        // side effect
                        println("(degree-min (clock-degree (clock-str " +
                                "\"$clockStr\")) " +
                                "\n> ${degree.value}") }
                    .then { (clockStr, degree) ->
                        Assert.that(degree.value == 115,
                                "$clockStr actual:${degree.value} expected:" + 115)
                    }
        }



class Fixture(val aTime: Int, val aDegree: Int)

private inline fun <T> T.then(block: (T) -> Unit) { block(this)}