package bdd4J.test.helpers;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Created by mateusz.urban on 10/7/2014.
 */
public class RunnerHelper {
    public static Result RunTest(Class testClass) {
        JUnitCore runner = new JUnitCore();
        return runner.run(testClass);
    }
}
