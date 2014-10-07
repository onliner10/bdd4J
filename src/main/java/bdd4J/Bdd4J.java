package bdd4J;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.runner.Description.createTestDescription;

/**
 * Created by Mateusz on 10/4/2014.
 */
public class Bdd4J extends Runner {
    private final Map<String, Estabilish> estabilishes;
    private final Map<String, Because> becauses;
    private final Map<String, It> its;
    private Class testClass;

    public Bdd4J(Class testClass) throws IllegalAccessException, InstantiationException {
        this.testClass = testClass;

        Object testInstance = testClass.newInstance();

        estabilishes = resolveFieldsOfType(Estabilish.class, testInstance);
        becauses = resolveFieldsOfType(Because.class, testInstance);
        its = resolveFieldsOfType(It.class, testInstance);
    }

    private static <T> Map<String, T> resolveFieldsOfType(Class<T> fieldType, Object testInstance) throws IllegalAccessException {
        Map<String, T> result = new HashMap<String, T>();

        for(Field field : testInstance.getClass().getDeclaredFields()) {
            if(field.getType() == fieldType) {
                field.setAccessible(true);

                T fieldValue = (T)field.get(testInstance);
                String fieldName = field.getName();

                result.put(fieldName, fieldValue);
            }
        }

        return result;
    }

    @Override
    public Description getDescription() {
         String suiteName = String.format("%s, %s, %s",testClass.getSimpleName(), firstOrDefault(getNamesFor(estabilishes)), "because " + firstOrDefault(getNamesFor(becauses)));
         Description description = Description.createSuiteDescription(suiteName);

         for(String itName : getNamesFor(its)){
            description.addChild(createTestDescription(testClass, "It " + itName));
         }
        //description.addChild(createTestDescription(testClass, "It passes correct assertion"));
        return description;
    }

    @Override
    public void run(RunNotifier runNotifier) {
        for (Estabilish estabilish : estabilishes.values()) {
            estabilish.invoke();
        }

        for(Because because : becauses.values()) {
            because.invoke();
        }

        for(It it : its.values()) {
            it.invoke();
        }
    }

    private String firstOrDefault(List<String> list) {
        if(list.size() == 0)
            return "";

        return list.get(0);
    }

    private <T> List<String> getNamesFor(Map<String, T> map) {
        List<String> result = new ArrayList<String>();

        for(String key : map.keySet()) {
            result.add(key.replace('_', ' '));
        }

        return result;
    }
}
