package pooh3.mobi.app.a01_clock.model;


import static pooh3.mobi.Preconditions.checkNotNull;

public class Clock {
    public final ShortHand shortHand;
    public final LongHand longHand;

    Clock(ShortHand shortHand, LongHand longHand) {
        this.shortHand = checkNotNull(shortHand);
        this.longHand = checkNotNull(longHand);
    }

    @Override
    public String toString() {
        return shortHand.hour + ":" + longHand.minute;
    }
}
