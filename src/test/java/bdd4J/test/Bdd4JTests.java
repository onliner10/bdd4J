package bdd4J.test;

import bdd4J.test.fixtures.NotNestedTest;
import org.junit.Test;
import org.junit.runner.*;

import org.junit.Assert;

/**
 * Created by Mateusz on 10/4/2014.
 */
public class Bdd4JTests {

    @Test
    public void itIsAbleToRunSingleEstabilish() {
        NotNestedTest.wasEstabilishRun = false;
        NotNestedTest.wasBecauseRun = false;

        Result result = RunTest(NotNestedTest.class);

        Assert.assertTrue("Estabilish was not run", NotNestedTest.wasEstabilishRun);
    }

    @Test
    public void itIsAbleToRunSingleBecause() {
        NotNestedTest.wasEstabilishRun = false;
        NotNestedTest.wasBecauseRun = false;

        Result result = RunTest(NotNestedTest.class);

        Assert.assertTrue("Because was not run, or estabilish was not run before becuase", NotNestedTest.wasBecauseRun);
    }

    private static Result RunTest(Class testClass) {
        JUnitCore runner = new JUnitCore();
        return runner.run(testClass);
    }
}
