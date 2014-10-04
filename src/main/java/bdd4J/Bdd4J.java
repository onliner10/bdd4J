package bdd4J;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.runner.Description.createTestDescription;

/**
 * Created by Mateusz on 10/4/2014.
 */
public class Bdd4J extends Runner {
    private final List<Estabilish> estabilishes;

    public Bdd4J(Class testClass) throws IllegalAccessException, InstantiationException {
        Object testInstance = testClass.newInstance();

        estabilishes = new ArrayList<Estabilish>();

        for(Field field : testClass.getDeclaredFields()) {
            if(field.getType() == Estabilish.class) {
                field.setAccessible(true);

                Estabilish e = (Estabilish)field.get(testInstance);
                estabilishes.add(e);
            }
        }
    }

    @Override
    public Description getDescription() {
        return null;
    }

    @Override
    public void run(RunNotifier runNotifier) {
        for (Estabilish estabilish : estabilishes) {
            estabilish.invoke();
        }
    }
}
