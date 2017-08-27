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

private val aShortHandTest_hour_to_degree:
        (shortHandFixtures: ArrayList<Fixture>) -> Unit =
        { list ->
            list.forEach {
                sf ->
                    sf.shortHand
                            .degree(0.longHand).int
                            .also {
                                // side effect
                                println("(hand-degree \n" +
                                        "    (short-hand ${sf.aTimeInt})) \n" +
                                        "> $it") }
                            .then {
                                Assert.that(it == sf.aDegreeInt,
                                        "${sf.aTimeInt} " +
                                                "actual:$it  " +
                                                "expected:${sf.aDegreeInt}")
                            }
            }
        }

private val aShortHandTest_hour_to_min_degree:
        (shortHandMinFixtures: ArrayList<Fixture>) -> Unit =
        { list ->
            list.forEach {
                sf ->
                    sf.shortHand
                            .degree(0.longHand)
                            .minusMin(0.degree)
                            .also {
                                // side effect
                                println("(degree-min (hand-degree \n" +
                                        "                (short-hand ${sf.aTimeInt})) \n" +
                                        "> ${it.int}") }
                            .then {
                                Assert.that(it.int == sf.aDegreeInt,
                                        "${sf.aTimeInt}" +
                                                "actual:${it.int} " +
                                                "expected:${sf.aDegreeInt} ")
                            }
            }
        }

private val aLongHandTest_minute_to_degree:
        (longHandFixtures : ArrayList<Fixture>) -> Unit =
        { list ->
            list.forEach {
                f ->
                    f.longHand.degree.int
                            .also {
                                // side effect
                                println(
                                        "(hand-degree \n" +
                                        "    (long-hand ${f.aTimeInt})) \n" +
                                        "> $it") }
                            .then { degreeInt ->
                                Assert.that(degreeInt == f.aDegreeInt,
                                        "${f.aTimeInt} " +
                                                "actual:$degreeInt " +
                                                "expected:${f.aDegreeInt}")
                            }
            }
        }


private val hour10minute10_answer115degree:
        () -> Unit =
        {
            ("10:10" to GetClockHandsDegree(DegreeCalcService()))
                    .map { (clockStr, getDegree) -> clockStr to getDegree.execute(clockStr) }
                    .also { (clockStr, degree) ->
                        // side effect
                        println("(degree-min (clock-degree \n" +
                                "                (clock-str \"$clockStr\")) \n" +
                                "\n> ${degree.int}") }
                    .then { (clockStr, degree) ->
                        Assert.that(degree.int == 115,
                                "$clockStr actual:${degree.int} expected:" + 115)
                    }
        }



private class Fixture(val aTimeInt: Int, val aDegreeInt: Int)
private val Fixture.longHand: LongHand get() = this.aTimeInt.longHand
private val Fixture.shortHand: ShortHand get() = this.aTimeInt.shortHand
private inline fun <T, R> T.map(block: (T) -> R): R = block(this)
private inline fun <T> T.then(block: (T) -> Unit) { block(this)}

