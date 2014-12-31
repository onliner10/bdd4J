package com.github.onliner10.bdd4J.test.fixtures;

import com.github.onliner10.bdd4J.Bdd4J;
import com.github.onliner10.bdd4J.delegates.Because;
import com.github.onliner10.bdd4J.delegates.It;
import com.github.onliner10.bdd4J.statements.Catch;
import com.github.onliner10.bdd4J.statements.CaughtException;
import junit.framework.Assert;
import org.junit.runner.RunWith;

/**
 * Created by krzysztof.pawlowski on 30/12/14.
 */

@RunWith(Bdd4J.class)
public class CatchTest {

    static CaughtException exception;

    Because of = () -> { exception = Catch.exception(() -> Assert.assertEquals("expected", "actual, because of which it will fail")); };

    It should_fail = () -> exception.shouldBeOfType(junit.framework.ComparisonFailure.class);

}
