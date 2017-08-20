package pooh3.mobi.app.a01_clock.model;

import static pooh3.mobi.Preconditions.checkArgument;
import static pooh3.mobi.Preconditions.checkNotNull;

public class ClockFactory {

    private static final String DIV = ":";
    private static final int HOUR_INDEX = 0;
    private static final int MINUTE_INDEX = 1;


    public static Clock createByStr(String hourAndMinute) {
        String[] pair =
                checkNotNull(hourAndMinute,
                        "hourAndMinute require not null.").split(DIV);

        checkArgument(pair.length == 2,
                "check format:" + hourAndMinute);

        return new Clock(
                new ShortHand(Integer.parseInt(pair[HOUR_INDEX])),
                new LongHand(Integer.parseInt(pair[MINUTE_INDEX]))
        );
    }
}
