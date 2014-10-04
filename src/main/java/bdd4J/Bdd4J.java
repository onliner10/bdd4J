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
    private final List<Because> becauses;
    private final List<It> its;

    public Bdd4J(Class testClass) throws IllegalAccessException, InstantiationException {
        Object testInstance = testClass.newInstance();

        estabilishes = resolveFieldsOfType(Estabilish.class, testInstance);
        becauses = resolveFieldsOfType(Because.class, testInstance);
        its = resolveFieldsOfType(It.class, testInstance);
    }

    private static <T> List<T> resolveFieldsOfType(Class<T> fieldType, Object testInstance) throws IllegalAccessException {
        List<T> result = new ArrayList<T>();

        for(Field field : testInstance.getClass().getDeclaredFields()) {
            if(field.getType() == fieldType) {
                field.setAccessible(true);

                T e = (T)field.get(testInstance);
                result.add(e);
            }
        }

        return result;
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

        for(Because because : becauses) {
            because.invoke();
        }

        for(It it : its) {
            it.invoke();
        }
    }
}
