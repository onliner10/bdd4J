package com.github.onliner10.bdd4J.test.fixtures;

import com.github.onliner10.bdd4J.delegates.Because;
import com.github.onliner10.bdd4J.delegates.Establish;
import com.github.onliner10.bdd4J.delegates.It;
import com.github.onliner10.bdd4J.Bdd4J;
import junit.framework.Assert;
import org.junit.runner.RunWith;

/**
 * Created by mateusz.urban on 10/7/2014.
 */
@RunWith(Bdd4J.class)
public class MultipleItsTest {
    Establish some_establish = () -> { };

    Because of_some_because = () -> { };

    It should_do_sth_1 = () -> Assert.assertTrue(false);

    It should_do_sth_2 = () -> Assert.assertTrue(false);

    It should_do_sth_3 = () -> { };
}
