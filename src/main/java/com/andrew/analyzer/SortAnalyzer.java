package com.andrew.analyzer;


import lombok.Getter;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


/**
 *
 */
@SuppressWarnings("ALL")
public class SortAnalyzer {


    /**
     * Map contains class names and sort methods to be analyzed
     */
    private Map<Class, Method> sortClassMap;

    /**
     * List contains arrays initialization methods to be used
     */
    private List<Method> initMethods;

    /**
     * Map contains analyzed data. (InitMethod -> (ClassName -> (ArraySize - Seconds)))
     */
    @Getter
    private Map<String, Map<String, Map<Integer, Double>>> myData;
    /**
     * Map contains array size and seconds
     */
    @Getter
    private Map<Integer, Double> arraySizeToSec;

    /**
     * Map contains class name
     */
    private Map<String, Map<Integer, Double>> classToArraySizeAndSec;

    /**
     *
     */
    private Map<String, Map<String, Map<Integer, Double>>> initMethodtoClassToArraySizeAndSec;

    /**
     * array consists of array sizes for init methods
     */
    @Getter
    private final int[] testData;

    /**
     * Constructor
     *
     * @param testData {@code array} with test array sizes
     */
    public SortAnalyzer(int[] testData) {
        this.testData = testData;
    }


    /**
     * Method searches all subclasses from superclass
     *
     * @param className {@code String} name of a superclass to analyze
     * @return {@code set}of subclasses
     */
    private Set getSortGrandChildren(String className) {
        Class cl = null;
        try {
            cl = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert cl != null;
        Reflections s = new Reflections(cl.getPackage().getName());
        //noinspection unchecked
        return s.getSubTypesOf(cl);
    }

    /**
     * Method searches for sorting methods in subclasses
     *
     * @param classSet       {@code set} of subclasses
     * @param sortMethodName sort method name
     */
    private void getAllSortMethods(Set<Class> classSet, String sortMethodName) {
        //look through sort classes
        sortClassMap = new HashMap<>();
        for (Class cl : classSet) {
            Method[] allClassMethods = cl.getDeclaredMethods();
            for (Method sortMethod : allClassMethods) {
                //checks for a sort method
                if (sortMethod.getName().equals(sortMethodName)) {
                    sortClassMap.put(cl, sortMethod);
                }
            }
        }
    }

    /**
     * Method analyze time efficiency of sorting methods
     *
     * @param className  name of a sort class to analyze
     * @param filterName name of init class to analyze
     */
    public void analyze(String className, String filterName) {
        @SuppressWarnings("unchecked") Set<Class> subClasses = getSortGrandChildren(className);
        initMethods = getArrayInitMethods(filterName);
        getAllSortMethods(subClasses, "doSort");

        try {
            myData = getSortTimeResults2();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * Calculates sort time for each sort method
     *
     * @return map that contains time results, class names, init methods
     * @throws InstantiationException    if this Class represents an abstract class, an interface,
     *                                   an array class, a primitive type, or void; or if the class has no nullary constructor;
     *                                   or if the instantiation fails for some other reason.
     * @throws IllegalAccessException    if this Method object is enforcing Java language access
     *                                   control and the underlying method is inaccessible.
     * @throws InvocationTargetException if the underlying method throws an exception.
     */
    private Map<String, Map<String, Map<Integer, Double>>> getSortTimeResults2() throws InstantiationException, IllegalAccessException, InvocationTargetException {

        initMethodtoClassToArraySizeAndSec = new HashMap<>();
        //look through init methods
        for (Method method : initMethods) {
            classToArraySizeAndSec = new HashMap<>();
            //look through class names and sort methods
            for (Map.Entry<Class, Method> item : sortClassMap.entrySet()) {
                //new instance of sort class
                Object obj;
                obj = item.getKey().newInstance();
                arraySizeToSec = new TreeMap<>();
                //going through different array sizes
                for (Integer N : testData) {
                    //invoking init method with N size
                    Integer[] array1 = (Integer[]) method.invoke(null, N);
                    //time counter
                    long count = timeCount(item.getValue(), obj, array1);
                    double seconds = (double) count / 1000000000.0;
                    arraySizeToSec.put(N, seconds);
                }
                classToArraySizeAndSec.put(item.getKey().getName(), arraySizeToSec);
            }
            initMethodtoClassToArraySizeAndSec.put(method.getName(), classToArraySizeAndSec);
        }
        return initMethodtoClassToArraySizeAndSec;

    }


    /**
     * Searches and stores initialization array methods
     *
     * @param className init class name
     * @return list contains init methods
     */
    private List<Method> getArrayInitMethods(String className) {
        List<Method> methodsList = new ArrayList<>();
        Class arrayInitClass = null;
        try {
            arrayInitClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert arrayInitClass != null;
        Method[] allMethods = arrayInitClass.getMethods();
        for (Method initMethod : allMethods) {
            if (Modifier.isStatic(initMethod.getModifiers())) {
                methodsList.add(initMethod);
            }
        }
        return methodsList;
    }

    /**
     * Counts sorting method time
     *
     * @param methodToBeMeasured measured method
     * @param obj                instance that contains measured method
     * @param array              test array to measure sort time
     * @return sorting time
     */
    private long timeCount(Method methodToBeMeasured, Object obj, Comparable[] array) {
        try {
            long start = System.nanoTime();
            methodToBeMeasured.invoke(obj, new Object[]{array});
            return System.nanoTime() - start;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
