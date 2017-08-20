package pooh3.mobi.app.a01_clock.model;

public class DegreeCalcService {

    public Degree degreeBetweenClockTwoHands(Clock c) {
        return c.longHand.degree()
                .minusMin(c.shortHand.degree(c.longHand));
    }
}
