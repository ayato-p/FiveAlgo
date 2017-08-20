package pooh3.mobi;

import pooh3.mobi.app.a01_clock.model.TestClock;

public class Main {

    public static void main(String[] args) {

        // test
        test();
    }

    private static void test() {
        try {
            doTests();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doTests() throws Exception {
        new TestClock().test();
    }
}
