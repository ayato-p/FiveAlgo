package pooh3.mobi.app.a01_clock.model;

import static pooh3.mobi.Preconditions.checkNotNull;

public class Degree {

    public final int val;

    private Degree(int val) {
        this.val = val;
    }

    public static Degree of(int degreeInt) {
        return new Degree(degreeInt);
    }

    public Degree minus(Degree degree) {
        return Degree.of(this.val - checkNotNull(degree).val);
    }

    public Degree minusAbs(Degree degree) {
        return Degree.of(
                Math.abs(
                        minus(checkNotNull(degree)).val));
    }

    public Degree minusMin(Degree degree) {
        final Degree calced = checkNotNull(minusAbs(degree));
        return calced.val <= 180 ? calced : Degree.of(360).minus(calced);
    }
}
