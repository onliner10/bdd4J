package bdd4J.test.fixtures;

import bdd4J.Bdd4J;
import bdd4J.Because;
import bdd4J.Estabilish;
import bdd4J.It;
import org.junit.Assert;
import org.junit.runner.RunWith;

/**
 * Created by Mateusz on 10/4/2014.
 */

@RunWith(Bdd4J.class)
public class NotNestedPassingTest {

    public static boolean wasEstabilishRun = false;
    public static boolean wasBecauseRun = false;
    public static boolean wasItRun = false;

    Estabilish was_estabilish_run_is_true = ()
            -> wasEstabilishRun = true;

    Because of_changing_variable_value = () -> {
        AssertEstabilishRun();

        wasBecauseRun = true;
    };

    It passes_correct_assertion = () -> {
        AssertEstabilishRun();
        AssertBecauseRun();
        wasItRun = true;

        Assert.assertTrue(true);
    };

    private void AssertEstabilishRun() {
        if(false == wasEstabilishRun) {
            throw new AssertionError("Estabilish was not run at the right time, or at all!");
        }
    }

    private void AssertBecauseRun() {
        if(false == wasBecauseRun) {
            throw new AssertionError("Because was not run at the right time, or at all!!");
        }
    }

}
