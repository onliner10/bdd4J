package bdd4J;

import bdd4J.delegates.Because;
import bdd4J.delegates.Cleanup;
import bdd4J.delegates.Estabilish;
import bdd4J.delegates.It;
import junit.framework.AssertionFailedError;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.omg.CORBA.portable.ApplicationException;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;

import static org.junit.runner.Description.createTestDescription;

/**
 * Created by Mateusz on 10/4/2014.
 */
public class Bdd4J extends Runner {
    private Map<It, Description> itToDescription;
    private Class testClass;

    public Bdd4J(Class testClass) {
        this.testClass = testClass;
        this.itToDescription = new HashMap<>();
    }

    @Override
    public Description getDescription() {
        return createSuiteRecursiveFor(testClass);
    }

    @Override
    public void run(RunNotifier runNotifier) {
        runClass(testClass, runNotifier);
    }

    private void runClass(Class classToRun, RunNotifier runNotifier) {
        Map<String, Estabilish> estabilishes = null;
        Map<String, Because> becauses = null;
        Map<String, It> its = null;
        Map<String, Cleanup> cleanups = null;

        try {
            Object testInstance = createInstanceOf(classToRun);

            estabilishes = resolveFieldsOfType(Estabilish.class, testInstance);
            becauses = resolveFieldsOfType(Because.class, testInstance);
            its = resolveFieldsOfType(It.class, testInstance);
            cleanups = resolveFieldsOfType(Cleanup.class, testInstance);
        } catch (Exception e) {
            throw new RuntimeException("Cannot resolve required delegates to run test");
        }

        for (Estabilish estabilish : estabilishes.values()) {
            estabilish.invoke();
        }

        for(Because because : becauses.values()) {
            because.invoke();
        }

        for(It it : its.values()) {
            Description testDescription = itToDescription.get(it);
            runNotifier.fireTestStarted(testDescription);

            try {
                it.invoke();
            }
            catch (AssertionFailedError e) {
                runNotifier.fireTestFailure(
                        new Failure(testDescription, e)
                );
            }

            runNotifier.fireTestFinished(testDescription);
        }

        List<Class> nestedClassesInCorrectOrder = Arrays.asList(classToRun.getDeclaredClasses());
        Collections.reverse(nestedClassesInCorrectOrder);

        for(Class nestedClass : nestedClassesInCorrectOrder) {
            runClass(nestedClass, runNotifier);
        }

        for(Cleanup c : cleanups.values()) {
            c.invoke();
        }
    }

    private Description createSuiteRecursiveFor(Class testClass) {
        return createSuiteRecursiveFor(testClass, null);
    }

    private Description createSuiteRecursiveFor(Class clazz, Description recursiveDescription) {
        String suiteName = suiteNameFor(clazz);
        Description description = Description.createSuiteDescription(suiteName);

        Map<String, It> its = null;
        try {
            its = resolveFieldsOfType(It.class, createInstanceOf(clazz));
        } catch (Exception e) {
            throw new RuntimeException("Cannot resolve required delegates to run test");
        }

        for(Map.Entry<String, It> it : its.entrySet()){
            Description testDescription =
                    createTestDescription(
                        clazz,
                        "It " + it.getKey().replace('_', ' '));

            itToDescription.put(it.getValue(), testDescription);
            description.addChild(testDescription);
        }
        recursiveDescription = addAsChildTo(description, recursiveDescription);

        List<Class> nestedClassesInCorrectOrder = Arrays.asList(clazz.getDeclaredClasses());
        Collections.reverse(nestedClassesInCorrectOrder);

        for(Class nestedClass : nestedClassesInCorrectOrder) {
            createSuiteRecursiveFor(nestedClass, description);
        }

        return recursiveDescription;
    }

    private Description addAsChildTo(Description description, Description recursiveDescription) {
        if(recursiveDescription == null) {
            recursiveDescription = description;
        }
        else {
            recursiveDescription.addChild(description);
        }
        return recursiveDescription;
    }

    private String suiteNameFor(Class clazz) {
        Map<String, Because> becauses = null;

        try {
            becauses = resolveFieldsOfType(Because.class, createInstanceOf(clazz));
        } catch (Exception e) {
            throw new RuntimeException("Cannot resolve required delegates to run test");
        }

        String becauseName = firstOrDefault(getNamesFor(becauses));

        if(becauseName != "") {
            return MessageFormat.format("{0}, {1}",
                    clazz.getSimpleName().replace('_', ' '),
                    "because " + becauseName);
        }

        return clazz.getSimpleName().replace('_', ' ');
    }

    private Object createInstanceOf(Class clazz) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Object classInstance = null;
        if(clazz == testClass) {
            classInstance = clazz.newInstance();
        } else {
            classInstance = clazz.getConstructors()[0].newInstance(testClass.newInstance());
        }
        return classInstance;
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
}
