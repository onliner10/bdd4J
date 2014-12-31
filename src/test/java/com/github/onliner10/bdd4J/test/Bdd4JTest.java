package com.github.onliner10.bdd4J.test;

import com.github.onliner10.bdd4J.Bdd4J;
import com.github.onliner10.bdd4J.delegates.Because;
import com.github.onliner10.bdd4J.delegates.Establish;
import com.github.onliner10.bdd4J.delegates.It;
import com.github.onliner10.bdd4J.test.fixtures.FailingTest;
import com.github.onliner10.bdd4J.test.fixtures.MultipleItsTest;
import com.github.onliner10.bdd4J.test.fixtures.NotNestedPassingTest;
import com.github.onliner10.bdd4J.test.helpers.RunnerHelper;
import org.junit.Before;
import junit.framework.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.*;

import org.junit.Assert;
import org.junit.runner.notification.Failure;

import static org.junit.runner.Description.createTestDescription;

/**
 * Created by Mateusz on 10/4/2014.
 */
public class Bdd4JTest {

    @Before
    public void setUp() {
        NotNestedPassingTest.invokationOrder.clear();
    }

    @Test
    public void itIsAbleToRunSingleEstabilish() {
        Result result = RunnerHelper.RunTest(NotNestedPassingTest.class);

        Assert.assertEquals("Establish was not run", NotNestedPassingTest.invokationOrder.get(0), Establish.class);
    }

    @Test
    public void itIsAbleToRunSingleBecause() {
        Result result = RunnerHelper.RunTest(NotNestedPassingTest.class);

        Assert.assertEquals("Establish was not run", NotNestedPassingTest.invokationOrder.get(0), Establish.class);
        Assert.assertEquals("Because was not run", NotNestedPassingTest.invokationOrder.get(1), Because.class);
    }

    @Test
    public void testPassesWithSinglePassingIt() {
        Result result = RunnerHelper.RunTest(NotNestedPassingTest.class);

        Assert.assertEquals("Establish was not run", NotNestedPassingTest.invokationOrder.get(0), Establish.class);
        Assert.assertEquals("Because was not run", NotNestedPassingTest.invokationOrder.get(1), Because.class);
        Assert.assertEquals("It was not run", NotNestedPassingTest.invokationOrder.get(2), It.class);
    }

    @Test
    public void itShouldNoticeThatTestsStarted() {
        Result result = RunnerHelper.RunTest(NotNestedPassingTest.class);

        Assert.assertEquals(1, result.getRunCount());
    }

    @Test
    public void itShouldFailWithAppropiateException() {
        Result result = RunnerHelper.RunTest(FailingTest.class);
        Failure failure = result.getFailures().get(0);

        Assert.assertEquals(1, result.getFailureCount());
        Assert.assertEquals(ComparisonFailure.class, failure.getException().getClass());
        Assert.assertEquals(
                "expected:<[expected]> but was:<[actual, because of which it will fail]>",
                failure.getMessage());
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
