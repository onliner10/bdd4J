package bdd4J.test.fixtures;

import bdd4J.Bdd4J;
import bdd4J.delegates.It;
import junit.framework.Assert;
import org.junit.runner.RunWith;

/**
 * Created by Mateusz on 10/4/2014.
 */
@RunWith(Bdd4J.class)
public class FailingTest {
    It should_fail = () -> Assert.assertEquals("expected", "actual, because of which it will fail");
}
