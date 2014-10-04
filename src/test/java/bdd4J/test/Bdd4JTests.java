package bdd4J.test;

import bdd4J.test.fixtures.NotNestedPassingTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;

import org.junit.Assert;

/**
 * Created by Mateusz on 10/4/2014.
 */
public class Bdd4JTests {

    @Before
    public void setUp() {
        NotNestedPassingTest.wasEstabilishRun = false;
        NotNestedPassingTest.wasBecauseRun = false;
        NotNestedPassingTest.wasItRun = false;
    }

    @Test
    public void itIsAbleToRunSingleEstabilish() {
        Result result = RunTest(NotNestedPassingTest.class);

        Assert.assertTrue("Estabilish was not run", NotNestedPassingTest.wasEstabilishRun);
    }

    @Test
    public void itIsAbleToRunSingleBecause() {
        Result result = RunTest(NotNestedPassingTest.class);

        Assert.assertTrue("Because was not run, or estabilish was not run before becuase", NotNestedPassingTest.wasBecauseRun);
    }

    @Test
    public void testPassesWithSinglePassingIt() {
        Result result = RunTest(NotNestedPassingTest.class);

        Assert.assertTrue("It was not run", NotNestedPassingTest.wasItRun);
        Assert.assertTrue("Assertion failed, but should not", result.getFailures().size() == 0);
    }


    private static Result RunTest(Class testClass) {
        JUnitCore runner = new JUnitCore();
        return runner.run(testClass);
    }
}
