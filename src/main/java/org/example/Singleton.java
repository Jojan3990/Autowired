package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Singleton {
    final static Map<Class, Object> BEANS = new HashMap<>();
    public static synchronized Object getInstance(Class c) {
        return BEANS.get(c);
    }

    //  Assuming that there is no duplicate vertices in DFS. I haven't checked duplicate keys here
    public void makeInstance(Class c) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor constructor = c.getConstructors()[0];
        List<Object> parameters = new ArrayList<>();

        Class[] parameterTypes = constructor.getParameterTypes();
        for (Class parameterType : parameterTypes) {
            parameters.add(BEANS.get(parameterType));
        }
        BEANS.putIfAbsent(c, constructor.newInstance(parameters.toArray()));
    }
}
