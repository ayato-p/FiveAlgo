package pooh3.mobi.app.a01_clock.model;


import static pooh3.mobi.Preconditions.checkNotNull;

public class Clock {
    public final ShortHand shortHand;
    public final LongHand longHand;

    private Clock(ShortHand shortHand, LongHand longHand) {
        this.shortHand = checkNotNull(shortHand);
        this.longHand = checkNotNull(longHand);
    }

    public static Clock build(ShortHand shortHand, LongHand longHand) {
        return new Clock(shortHand, longHand);
    }

    @Override
    public String toString() {
        return shortHand.hour + ":" + longHand.minute;
    }
}
