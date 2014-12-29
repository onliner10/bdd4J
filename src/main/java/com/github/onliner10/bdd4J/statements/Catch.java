package com.github.onliner10.bdd4J.statements;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by krzysztof.pawlowski on 29/12/14.
 */
public class Catch {
    public static CaughtException exception(VoidFunction voidFunction) {
        try {
            voidFunction.invoke();
        }
        catch (Throwable e) {
            return new CaughtException(e);
        }
        throw new RuntimeException("Cannot catch any exception!");
    }
}
