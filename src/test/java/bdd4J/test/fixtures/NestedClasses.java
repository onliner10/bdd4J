package bdd4J.test.fixtures;

import bdd4J.Bdd4J;
import bdd4J.delegates.Because;
import bdd4J.delegates.Cleanup;
import bdd4J.delegates.Estabilish;
import bdd4J.delegates.It;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateusz.urban on 10/7/2014.
 */

@RunWith(Bdd4J.class)
public class NestedClasses {

    public static List<String> invokationOrder = new ArrayList<String>();
    Estabilish top_level_estabilish = () -> invokationOrder.add(TOP_LEVEL_ESTABILISH);

    Because top_level_because = () -> invokationOrder.add(TOP_LEVEL_BECAUSE);

    public class first_nested_class {

        Estabilish first_nested_estabilish = () -> invokationOrder.add(FIRST_NESTED_ESTABILISH);

        It first_nested_it = () -> invokationOrder.add(FIRST_NESTED_IT);

        Cleanup first_nested_cleanup = () -> invokationOrder.add(FIRST_NESTED_CLEANUP);

    }

    public class second_nested_class {
        Estabilish second_nested_estabilish = () -> invokationOrder.add(SECOND_NESTED_ESTABILISH);

        Because nested_because = () -> invokationOrder.add(SECOND_NESTED_BECAUSE);

        It second_nested_it = () -> invokationOrder.add(SECOND_NESTED_IT);

        Cleanup second_nested_cleanup = () -> invokationOrder.add(SECOND_NESTED_CLEANUP);
    }

    Cleanup top_level_cleanup = () -> invokationOrder.add(TOP_LEVEL_CLEANUP);

    public static int invokationOrderOf(String delegateName) {
        return invokationOrder.indexOf(delegateName);
    }

    public static final String TOP_LEVEL_ESTABILISH = "1st estabilish";
    public static final String TOP_LEVEL_BECAUSE = "1st because";
    public static final String TOP_LEVEL_CLEANUP = "1st cleanup";

    public static final String FIRST_NESTED_ESTABILISH = "1st nested estabilish";
    public static final String FIRST_NESTED_IT = "1st nested it";
    public static final String FIRST_NESTED_CLEANUP = "1st nested cleanup";

    public static final String SECOND_NESTED_ESTABILISH = "2nd nested estabilish";
    public static final String SECOND_NESTED_BECAUSE = "2nd nested because";
    public static final String SECOND_NESTED_IT = "2nd nested it";
    public static final String SECOND_NESTED_CLEANUP = "2nd nested cleanup";
}
