package com.github.onliner10.bdd4J.statements;

import com.github.onliner10.bdd4J.exceptions.ExpectedExceptionNotCaughtException;
import junit.framework.ComparisonFailure;

/**
 * Created by krzysztof.pawlowski on 29/12/14.
 */
public class CaughtException {

    private Throwable caughtException;

    public CaughtException(Throwable caughtException) {
        this.caughtException = caughtException;
    }

    public void shouldBeOfType(Class<? extends Throwable> exceptionClass) throws ExpectedExceptionNotCaughtException {
        if (false == caughtException.getClass().equals(exceptionClass)) {
            throw new ExpectedExceptionNotCaughtException();
        }
    }
}
