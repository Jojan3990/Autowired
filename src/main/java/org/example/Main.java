package org.example;

import org.example.Graph.Graph;
import org.example.Graph.GraphDFS;
import org.example.Graph.Vertex;
import org.example.Model.*;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        GraphDFS graphDFS = new GraphDFS();
        Graph g = new Graph();
        Class randomClass;
        Graph graph = null;

//      Using Reflection API to scan package org.example
        Reflections reflections = new Reflections("org.example");
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Component.class);
        Singleton singleton = new Singleton();
        ArrayList<Class> classes = new ArrayList<>();

//      Getting all vertices which are classes to add in a tree annotated with Component annotation
        for (Class c : typesAnnotatedWith) {
            classes.add(c);
        }
        graph = g.createGraph(classes);
        randomClass = classes.get(0); // getting random class from classes to traverse from

//      Adding edges in tree for DFS traversal i.e: edges being classes
        for (Class c : typesAnnotatedWith) {
            Constructor[] declaredConstructors = c.getDeclaredConstructors();
            for (Constructor constructor : declaredConstructors) {
                if (constructor.isAnnotationPresent(Autowired.class)) {
                    Class[] parameterTypes = constructor.getParameterTypes();
                    for (Class c1 : parameterTypes) {
                        graph.addEdge(c, c1);
                    }
                }
            }
        }

//      Doing DFS in component with autowired annotation and returning DFS search result
        List<Class> traversedListOfClasses = graphDFS.depthFirstTraversal(graph, randomClass);
        Collections.reverse(traversedListOfClasses);
        for (Class c : traversedListOfClasses) {
            singleton.makeInstance(c);
        }
    }
}