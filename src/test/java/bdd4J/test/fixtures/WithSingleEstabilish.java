package bdd4J.test.fixtures;

import bdd4J.Bdd4J;
import bdd4J.Estabilish;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Mateusz on 10/4/2014.
 */

@RunWith(Bdd4J.class)
public class WithSingleEstabilish {

    public static boolean wasEstabilishRun = false;

    Estabilish context = ()
            -> wasEstabilishRun = true;

}
