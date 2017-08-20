package pooh3.mobi.app.a01_clock.application

import pooh3.mobi.app.a01_clock.model.*

import pooh3.mobi.Preconditions.checkNotNull

class GetClockHandsDegree(degreeCalcService: DegreeCalcService) {

    private val degreeCalcService: DegreeCalcService = checkNotNull(degreeCalcService)

    fun execute(clockStr: String): Degree {
        return degreeCalcService.degreeBetweenClockTwoHands(
                ClockFactory.createByStr(clockStr))
    }
}
