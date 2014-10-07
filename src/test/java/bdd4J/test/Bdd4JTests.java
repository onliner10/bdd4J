package bdd4J.test;

import bdd4J.Bdd4J;
import bdd4J.test.fixtures.FailingTest;
import bdd4J.test.fixtures.MultipleItsTest;
import bdd4J.test.fixtures.NotNestedPassingTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;

import org.junit.Assert;

import static org.junit.runner.Description.createTestDescription;

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

    @Test
    public void testIsNotPassingWithSingleFailingIT() {
        Result result = RunTest(FailingTest.class);

        Assert.assertTrue("Assertion not failed, but should", result.getFailures().size() == 1);
    }

    @Test
    public void itCreatesCorrectDescription() throws InstantiationException, IllegalAccessException {
        Description expectedDescription = Description.createSuiteDescription("MultipleItsTest, some estabilish, because of some because");
        expectedDescription.addChild(createTestDescription(MultipleItsTest.class, "It should do sth 1"));
        expectedDescription.addChild(createTestDescription(MultipleItsTest.class, "It should do sth 2"));
        expectedDescription.addChild(createTestDescription(MultipleItsTest.class, "It should do sth 3"));

        Bdd4J bdd4j = new Bdd4J(MultipleItsTest.class);
        Description actualDescription = bdd4j.getDescription();

        Assert.assertEquals(expectedDescription, actualDescription);
        Assert.assertArrayEquals(
                expectedDescription.getChildren().toArray(),
                actualDescription.getChildren().toArray());
    }

    private static Result RunTest(Class testClass) {
        JUnitCore runner = new JUnitCore();
        return runner.run(testClass);
    }
}
