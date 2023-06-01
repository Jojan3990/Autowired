package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Singleton {
    static Map<Class, Object> Beans = new HashMap<>();

    public static synchronized Object getInstance(Class c) {
        return Beans.get(c);
    }

    //  Assuming that there is no duplicate vertices in DFS i haven't checked duplicate keys here
    public void makeInstance(Class c, Integer adjVerticesSize) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        ArrayList<Class> parameterClasses = new ArrayList<>();
        Constructor[] constructors = c.getConstructors();

//      For leaf vertices
        if (adjVerticesSize == 0) {
            Beans.putIfAbsent(c, c.newInstance());
        }

//      Vertices except leaf vertices
//      Assuming here that there is max of two parameter one constructor is containing
        else {
            for (Constructor constructor : constructors) {
                Class[] parameterTypes = constructor.getParameterTypes();
                for (Class parameterClassType : parameterTypes) {
                    parameterClasses.add(parameterClassType);
                }
                if (adjVerticesSize <= 1) {
                    Beans.putIfAbsent(c, constructor.newInstance(getInstance(parameterClasses.get(0))));
                } else {
//                    System.out.println( getInstance(parameterClasses.get(0)));
//                    System.out.println( getInstance(parameterClasses.get(1)));
                    Beans.putIfAbsent(c, constructor.newInstance(getInstance(parameterClasses.get(0)), getInstance(parameterClasses.get(1))));
                }
                parameterClasses.removeAll(parameterClasses);
            }
        }
    }


}
