package pooh3.mobi.app.a01_clock.model;

import static pooh3.mobi.Preconditions.checkArgument;

public class LongHand {
    public final int minute;
    public LongHand(int minute) {

        checkArgument(minute >= 0,
                "minute must not be negative:" + minute);
        checkArgument(minute < 60,
                "minute must less than 60:" + minute);

        this.minute = minute;
    }

    public Degree degree() {
        return Degree.of(360 / 60 * this.minute);
    }
}