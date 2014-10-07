package bdd4J.test;

import bdd4J.test.fixtures.NestedClasses;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;

import java.text.MessageFormat;

import static bdd4J.test.fixtures.NestedClasses.invokationOrderOf;
import static bdd4J.test.helpers.RunnerHelper.RunTest;

/**
 * Created by mateusz.urban on 10/7/2014.
 */
public class Bdd4JTests_NestedClasses {
    @Before
    public void cleanupTheInvokationList() {
        NestedClasses.invokationOrder.clear();
    }

    @Test
    public void itShouldInvokeEverythingStartingFromTopLevelOnes() {
        Result result = RunTest(NestedClasses.class);

        String estabilishesNotInTheRightOrder = "Estabilishes are not invoked in the right order";
        String itsNotInTheRightOrder = "Estabilishes are not invoked in the right order";

        Assert.assertEquals(estabilishesNotInTheRightOrder,              0, invokationOrderOf(NestedClasses.TOP_LEVEL_ESTABILISH));
        Assert.assertEquals("Because is not invoked in the right order", 1, invokationOrderOf(NestedClasses.TOP_LEVEL_BECAUSE));

        Assert.assertEquals(estabilishesNotInTheRightOrder,              2, invokationOrderOf(NestedClasses.FIRST_NESTED_ESTABILISH));
        Assert.assertEquals(itsNotInTheRightOrder,                       3, invokationOrderOf(NestedClasses.FIRST_NESTED_IT));

        Assert.assertEquals(estabilishesNotInTheRightOrder,              4, invokationOrderOf(NestedClasses.SECOND_NESTED_ESTABILISH));
        Assert.assertEquals(itsNotInTheRightOrder,                       5, invokationOrderOf(NestedClasses.SECOND_NESTED_IT));
    }
}
