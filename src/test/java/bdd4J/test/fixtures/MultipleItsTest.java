package bdd4J.test.fixtures;

import bdd4J.*;
import bdd4J.delegates.Because;
import bdd4J.delegates.Estabilish;
import bdd4J.delegates.It;
import junit.framework.Assert;
import org.junit.runner.RunWith;

/**
 * Created by mateusz.urban on 10/7/2014.
 */
@RunWith(Bdd4J.class)
public class MultipleItsTest {
    Estabilish some_estabilish = () -> {  };

    Because of_some_because = () -> { };

    It should_do_sth_1 = () -> {
        Assert.assertTrue(false); };

    It should_do_sth_2 = () -> {Assert.assertTrue(false); };

    It should_do_sth_3 = () -> { };
}
