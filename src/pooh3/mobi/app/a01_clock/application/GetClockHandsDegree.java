package pooh3.mobi.app.a01_clock.application;

import pooh3.mobi.app.a01_clock.model.*;

import static pooh3.mobi.Preconditions.checkNotNull;

public class GetClockHandsDegree {

    private final DegreeCalcService degreeCalcService;

    public GetClockHandsDegree(DegreeCalcService degreeCalcService) {
        this.degreeCalcService = checkNotNull(degreeCalcService);
    }

    public Degree execute(String clockStr) {
        return degreeCalcService.degreeBetweenClockTwoHands(
                ClockFactory.createByStr(clockStr));
    }
}
