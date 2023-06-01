package org.example.Graph;

import java.util.*;

public class Graph {
    private Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

    void addVertex(Object c) {
        adjVertices.putIfAbsent(new Vertex(c), new ArrayList<>());
    }

    public void addEdge(Class c1, Class c2) {
        Vertex v1 = new Vertex(c1);
        Vertex v2 = new Vertex(c2);
        adjVertices.get(v1).add(v2);
//        adjVertices.get(v2).add(v1); // commented to make graph directed graph
    }

    public Graph createGraph(ArrayList<Class> classes) {
        Graph graph = new Graph();
        for (Class c : classes) {
            graph.addVertex(c);
        }
        return graph;
    }

    public List<Vertex> getAdjVertices(Object obj) {
        return adjVertices.get(new Vertex(obj));
    }

    public List<Object> getAllVertices() {
        ArrayList<Object> vertices = new ArrayList<>();
        for (Vertex v : adjVertices.keySet()) {
            vertices.add((Class) v.object);
        }
        return vertices;
    }

}
