package com.github.onliner10.bdd4J.test;

import com.github.onliner10.bdd4J.Bdd4J;
import com.github.onliner10.bdd4J.test.fixtures.NestedClasses;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Result;

import static com.github.onliner10.bdd4J.test.fixtures.NestedClasses.invokationOrderOf;
import static com.github.onliner10.bdd4J.test.helpers.RunnerHelper.RunTest;

/**
 * Created by mateusz.urban on 10/7/2014.
 */
public class Bdd4JTests_NestedClasses {
    @Before
    public void cleanupTheInvokationList() {
        NestedClasses.invokationOrder.clear();
    }

    @Test
    public void itShouldCreateSuiteWithNestedClasses() throws InstantiationException, IllegalAccessException {
        Description expectedDescription = Description.createSuiteDescription("NestedClasses, because top level because");

        Description firstNestedDescription =
                Description.createSuiteDescription("first nested class");

        firstNestedDescription.addChild(
                Description.createTestDescription(
                        NestedClasses.first_nested_class.class,
                        "It first nested it"));

        expectedDescription.addChild(firstNestedDescription);

        Description secondNestedDescription =
                Description.createSuiteDescription("second nested class, because nested because");

        secondNestedDescription.addChild(
                Description.createTestDescription(
                        NestedClasses.second_nested_class.class,
                        "It second nested it"));

        expectedDescription.addChild(secondNestedDescription);

        Bdd4J bdd4j = new Bdd4J(NestedClasses.class);
        Description actualDescription = bdd4j.getDescription();

        Assert.assertEquals(expectedDescription, actualDescription);
        Assert.assertArrayEquals(
                expectedDescription.getChildren().toArray(),
                actualDescription.getChildren().toArray());
    }

    @Test
    public void itShouldInvokeEverythingStartingFromTopLevelOnes() {
        Result result = RunTest(NestedClasses.class);

        String estabilishesNotInTheRightOrder = "Estabilishes are not invoked in the right order";
        String itsNotInTheRightOrder = "Its are not invoked in the right order";
        String cleanupNotInTheRightOrder = "Cleanups are not invoked in the right order";
        String becauseNotInRightOrder = "Because is not invoked in the right order";

        Assert.assertEquals(estabilishesNotInTheRightOrder,              0, invokationOrderOf(NestedClasses.TOP_LEVEL_ESTABILISH));
        Assert.assertEquals(becauseNotInRightOrder,                      1, invokationOrderOf(NestedClasses.TOP_LEVEL_BECAUSE));

        Assert.assertEquals(estabilishesNotInTheRightOrder,              2, invokationOrderOf(NestedClasses.FIRST_NESTED_ESTABILISH));
        Assert.assertEquals(itsNotInTheRightOrder,                       3, invokationOrderOf(NestedClasses.FIRST_NESTED_IT));
        Assert.assertEquals(cleanupNotInTheRightOrder,                   4, invokationOrderOf(NestedClasses.FIRST_NESTED_CLEANUP));

        Assert.assertEquals(estabilishesNotInTheRightOrder,              5, invokationOrderOf(NestedClasses.SECOND_NESTED_ESTABILISH));
        Assert.assertEquals(estabilishesNotInTheRightOrder,              6, invokationOrderOf(NestedClasses.SECOND_NESTED_BECAUSE));
        Assert.assertEquals(itsNotInTheRightOrder,                       7, invokationOrderOf(NestedClasses.SECOND_NESTED_IT));
        Assert.assertEquals(cleanupNotInTheRightOrder,                   8, invokationOrderOf(NestedClasses.SECOND_NESTED_CLEANUP));

        Assert.assertEquals(cleanupNotInTheRightOrder,                   9, invokationOrderOf(NestedClasses.TOP_LEVEL_CLEANUP));
    }
}
