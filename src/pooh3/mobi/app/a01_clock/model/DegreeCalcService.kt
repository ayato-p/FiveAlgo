package pooh3.mobi.app.a01_clock.model

class DegreeCalcService {

    fun degreeBetweenClockTwoHands(c: Clock): Degree {
        return c.longHand.degree()
                .minusMin(c.shortHand.degree(c.longHand))
    }
}
