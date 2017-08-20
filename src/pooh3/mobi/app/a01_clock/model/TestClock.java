package pooh3.mobi.app.a01_clock.model;

import pooh3.mobi.app.a01_clock.application.GetClockHandsDegree;
import sun.jvm.hotspot.utilities.Assert;

import java.util.ArrayList;
import java.util.List;

public class TestClock {

    private List<Fixture>  longHandFixtures = new ArrayList<>();
    private List<Fixture>  shortHandFixtures = new ArrayList<>();
    private List<Fixture>  shortHandMinFixtures = new ArrayList<>();

    {
        longHandFixtures.add(new Fixture(0, 0));
        longHandFixtures.add(new Fixture(15, 90));
        longHandFixtures.add(new Fixture(30, 180));
        longHandFixtures.add(new Fixture(45, 270));
//        longHandFixtures.add(new Fixture(60, 360)); // exception

        shortHandFixtures.add(new Fixture(0, 0));
        shortHandFixtures.add(new Fixture(3, 90));
        shortHandFixtures.add(new Fixture(6, 180));
        shortHandFixtures.add(new Fixture(9, 270));
        shortHandFixtures.add(new Fixture(12, 0));
        shortHandFixtures.add(new Fixture(15, 90));
        shortHandFixtures.add(new Fixture(18, 180));
        shortHandFixtures.add(new Fixture(21, 270));

        shortHandMinFixtures.add(new Fixture(0, 0));
        shortHandMinFixtures.add(new Fixture(3, 90));
        shortHandMinFixtures.add(new Fixture(6, 180));
        shortHandMinFixtures.add(new Fixture(9, 90));
        shortHandMinFixtures.add(new Fixture(12, 0));
        shortHandMinFixtures.add(new Fixture(15, 90));
        shortHandMinFixtures.add(new Fixture(18, 180));
        shortHandMinFixtures.add(new Fixture(21, 90));
    }

    public void test() throws Exception {
        setUp();
        aLongHandTest_minute_to_degree();
        aShortHandTest_hour_to_degree();
        aShortHandTest_hour_to_min_degree();
        hour10minute10_answer115degree();
    }

    public void setUp() {
    }

    public void aLongHandTest_minute_to_degree() throws Exception {
        for (Fixture longHandFixture : longHandFixtures) {

            int degree =
                    LongHand.of(longHandFixture.aTime).degree().val;

            System.out.println(
                    "(hand-degree (long-hand " + longHandFixture.aTime
                            + ")) -> " + degree
                            + " == " + longHandFixture.aDegree);

            Assert.that(degree == longHandFixture.aDegree,
                    longHandFixture.aTime +" actual:"
                            + degree + " expected:" + longHandFixture.aDegree);
        }
    }

    public void aShortHandTest_hour_to_degree() throws Exception {
        for (Fixture shortHandFixture : shortHandFixtures) {

            int degree =
                    ShortHand.of(shortHandFixture.aTime)
                            .degree(LongHand.of(0)).val;

            System.out.println(
                    "(hand-degree (short-hand " + shortHandFixture.aTime
                            + ")) -> " + degree
                            + " == " + shortHandFixture.aDegree);

            Assert.that(degree == shortHandFixture.aDegree,
                    shortHandFixture.aTime +" actual:"
                            + degree + " expected:" + shortHandFixture.aDegree);
        }
    }

    public void aShortHandTest_hour_to_min_degree() throws Exception {
        for (Fixture shortHandFixture : shortHandMinFixtures) {

            Degree minDegree =
                    ShortHand.of(shortHandFixture.aTime).degree(LongHand.of(0))
                            .minusMin(Degree.of(0));

            System.out.println(
                    "(degree-min (hand-degree (short-hand " + shortHandFixture.aTime
                            + ")) -> " + minDegree.val
                            + " == " + shortHandFixture.aDegree);

            Assert.that(minDegree.val == shortHandFixture.aDegree,
                    shortHandFixture.aTime + " actual:"
                            + minDegree.val + " expected:" + shortHandFixture.aDegree);
        }
    }

    public void hour10minute10_answer115degree() {
        String c = "10:10";
        DegreeCalcService dcs = new DegreeCalcService();
        Degree degree = new GetClockHandsDegree(dcs).execute(c);

        System.out.println(
                "(degree-min (clock-degree (clock-str \"" + c
                        + "\")) -> " + degree.val
                        + " == " + 115);

        Assert.that(degree.val == 115,
                c + " actual:"
                        + degree.val + " expected:" + 115);
    }

    static class Fixture {
        public final int aTime;
        public final int aDegree;

        Fixture(int aTime, int aDegree) {
            this.aTime = aTime;
            this.aDegree = aDegree;
        }
    }
}
