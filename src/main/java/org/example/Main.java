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

        Reflections reflections = new Reflections("org.example");
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Component.class);
        Singleton singleton = new Singleton();
        ArrayList<Class> listOfClasses = new ArrayList<>();

//        Getting all vertices which are classes to add in a tree
        for (Class c : typesAnnotatedWith) {
            listOfClasses.add(c);
        }
        graph = g.createGraph(listOfClasses);
        randomClass = listOfClasses.get(0);

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

//      Doing BFS in component with autowired annotation and returning DFS search result
        List<Class> traversedListOfClasses = graphDFS.depthFirstTraversal(graph, randomClass);
        Collections.reverse(traversedListOfClasses);
        for (Class c : traversedListOfClasses) {
            List<Vertex> adjVertices = graph.getAdjVertices(c);
            singleton.makeInstance(c, adjVertices.size());
        }
    }
}