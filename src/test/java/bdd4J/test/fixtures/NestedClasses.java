package bdd4J.test.fixtures;

import bdd4J.Bdd4J;
import bdd4J.*;
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

    class first_nested_class {

        Estabilish first_nested_estabilish = () -> invokationOrder.add(FIRST_NESTED_ESTABILISH);

        It first_nested_it = () -> invokationOrder.add(FIRST_NESTED_IT);

    }
    class second_nested_class {

        Estabilish second_nested_estabilish = () -> invokationOrder.add(SECOND_NESTED_ESTABILISH);

        It second_nested_it = () -> invokationOrder.add(SECOND_NESTED_ESTABILISH);

    }

    public static int invokationOrderOf(String delegateName) {
        return invokationOrder.indexOf(delegateName);
    }

    public static final String TOP_LEVEL_ESTABILISH = "1st estabilish";
    public static final String TOP_LEVEL_BECAUSE = "1st because";
    public static final String FIRST_NESTED_ESTABILISH = "1st nested estabilish";
    public static final String FIRST_NESTED_IT = "1st nested it";
    public static final String SECOND_NESTED_ESTABILISH = "2nd nested estabilish";
    public static final String SECOND_NESTED_IT = "2nd nested it";
}
