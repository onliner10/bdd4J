package bdd4J.test.fixtures;

import bdd4J.Bdd4J;
import bdd4J.Because;
import bdd4J.Estabilish;
import org.junit.runner.RunWith;

/**
 * Created by Mateusz on 10/4/2014.
 */

@RunWith(Bdd4J.class)
public class NotNestedTest {

    public static boolean wasEstabilishRun = false;
    public static boolean wasBecauseRun = false;

    Estabilish context = ()
            -> wasEstabilishRun = true;

    Because of_changing_variable_value = () -> {
        if(false == wasEstabilishRun) {
            throw new AssertionError("Estabilish was not run before because!");
        }

        wasBecauseRun = true;
    };

}
