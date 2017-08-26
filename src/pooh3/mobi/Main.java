package pooh3.mobi;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class Main {

    public static void main(String[] args) {
        Clojure.var("clojure.core", "require")
                .invoke(Clojure.read("clojure-five-algo.core"));
        IFn func = Clojure.var("clojure-five-algo.core", "foo");
        func.invoke();

    }

}
