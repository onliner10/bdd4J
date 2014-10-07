package bdd4J.test;

import bdd4J.test.fixtures.NestedClasses;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;

import java.text.MessageFormat;

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
    public void itShouldInvokeEstabilishesStartingFromTopLevelOne() {
        Result result = RunTest(NestedClasses.class);

        int topLevelEstabilishInvokationOrder = NestedClasses.invokationOrder.indexOf(NestedClasses.TOP_LEVEL_ESTABILISH);
        int firstNestedEstabilishInvokationOrder = NestedClasses.invokationOrder.indexOf(NestedClasses.FIRST_NESTED_ESTABILISH);
        int secondNestedEstabilishInvokationOrder = NestedClasses.invokationOrder.indexOf(NestedClasses.SECOND_NESTED_ESTABILISH);

        String estabilishesNotInTheRightOrder = "Estabilishes are not invoked in the right order";

        Assert.assertEquals(estabilishesNotInTheRightOrder, 0, topLevelEstabilishInvokationOrder);
        Assert.assertEquals(estabilishesNotInTheRightOrder, 2, firstNestedEstabilishInvokationOrder);
        Assert.assertEquals(estabilishesNotInTheRightOrder, 4, secondNestedEstabilishInvokationOrder);
    }
}
