package pooh3.mobi.app.a01_clock.model;

import static pooh3.mobi.Preconditions.checkArgument;
import static pooh3.mobi.Preconditions.checkNotNull;

public class ShortHand {
    public final int hour;
    public ShortHand(int hour) {

        checkArgument(hour >= 0,
                "hour must not be negative:" + hour);
        checkArgument(hour < 24,
                "hour must less than 24:" + hour);

        this.hour = hour % 12;
    }

    public Degree degree(LongHand longHand) {
        return Degree.of(360/12 * this.hour + additionalDegree(checkNotNull(longHand).minute));
    }

    private int additionalDegree(int minute) {
        return (minute == 0 ? 0 : 30/(60/minute));
    }
}