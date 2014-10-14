package com.github.onliner10.bdd4J.delegates;

/**
 * Created by mateusz.urban on 10/7/2014.
 */

/**
 * Executes after each test context. Used for cleaning up resources used by test.
 */
public interface Cleanup {
    void invoke();
}
