package bdd4J.test;

import bdd4J.test.fixtures.WithSingleEstabilish;
import org.junit.Test;
import org.junit.runner.*;

import org.junit.Assert;

/**
 * Created by Mateusz on 10/4/2014.
 */
public class Bdd4JTests {

    @Test
    public void itIsAbleToRunSingleEstabilish() {

        JUnitCore runner = new JUnitCore();
        Result result = runner.run(WithSingleEstabilish.class);

        Assert.assertTrue("Estabilish was not run", WithSingleEstabilish.wasEstabilishRun);
    }
}
