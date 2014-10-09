package bdd4J.test;

import bdd4J.Bdd4J;
import bdd4J.delegates.Because;
import bdd4J.delegates.Estabilish;
import bdd4J.delegates.It;
import bdd4J.test.fixtures.FailingTest;
import bdd4J.test.fixtures.MultipleItsTest;
import bdd4J.test.fixtures.NotNestedPassingTest;
import bdd4J.test.helpers.RunnerHelper;
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
        NotNestedPassingTest.invokationOrder.clear();
    }

    @Test
    public void itIsAbleToRunSingleEstabilish() {
        Result result = RunnerHelper.RunTest(NotNestedPassingTest.class);

        Assert.assertEquals("Estabilish was not run", NotNestedPassingTest.invokationOrder.get(0), Estabilish.class);
    }

    @Test
    public void itIsAbleToRunSingleBecause() {
        Result result = RunnerHelper.RunTest(NotNestedPassingTest.class);

        Assert.assertEquals("Estabilish was not run", NotNestedPassingTest.invokationOrder.get(0), Estabilish.class);
        Assert.assertEquals("Because was not run", NotNestedPassingTest.invokationOrder.get(1), Because.class);
    }

    @Test
    public void testPassesWithSinglePassingIt() {
        Result result = RunnerHelper.RunTest(NotNestedPassingTest.class);

        Assert.assertEquals("Estabilish was not run", NotNestedPassingTest.invokationOrder.get(0), Estabilish.class);
        Assert.assertEquals("Because was not run", NotNestedPassingTest.invokationOrder.get(1), Because.class);
        Assert.assertEquals("It was not run", NotNestedPassingTest.invokationOrder.get(2), It.class);
    }

    @Test
    public void testIsNotPassingWithSingleFailingIT() {
        Result result = RunnerHelper.RunTest(FailingTest.class);

        Assert.assertTrue("Assertion not failed, but should", result.getFailures().size() == 1);
    }

    @Test
    public void itCreatesCorrectDescription() throws InstantiationException, IllegalAccessException {
        Description expectedDescription = Description.createSuiteDescription("MultipleItsTest, because of some because");
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

}
