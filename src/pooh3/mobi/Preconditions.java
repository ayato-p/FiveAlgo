package pooh3.mobi;

public class Preconditions {

    public static <T> T checkNotNull(T reference) {
        return checkNotNull(reference, null);
    }

    public static <T> T checkNotNull(T reference, String msg) {
        if (reference == null)
            throw new NullPointerException(
                    msg != null ? msg : "reference Object is null");
        return reference;
    }

    public static void checkArgument(boolean condition) {
        checkArgument(condition, null);
    }

    public static void checkArgument(boolean condition, String msg) {
        if (!condition)
            throw new IllegalArgumentException(
                    msg != null ? msg : "argument condition is not required");
    }
}
