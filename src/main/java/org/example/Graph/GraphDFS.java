package org.example.Graph;

import org.example.Model.First;

import java.util.*;

public class GraphDFS {
    LinkedList<Class> visited = new LinkedList<>();
    List<Class> visitedDisconnectedVertices = new ArrayList<>();
    Stack<Object> stack = new Stack<>();
    List<Object> allVertices;

    public List<Class> depthFirstTraversal(Graph graph, Object root) {
        allVertices = graph.getAllVertices();
        stack.push(root);
        while (!stack.isEmpty()) {
            Object vertex = stack.pop();
            allVertices.remove(vertex);
            if (!visited.contains(vertex)) {
                visited.add((Class) vertex);
                for (Vertex v : graph.getAdjVertices(vertex)) {
                    stack.push(v.object);
                }
            }
            while (stack.isEmpty() && !allVertices.isEmpty()) {
                List<Class> classes = disconnectedGraph(graph, allVertices.get(0));
                Collections.reverse(classes);
                for (Class c : classes) {
                    visited.addFirst(c);
                }
                visitedDisconnectedVertices.removeAll(visitedDisconnectedVertices);
            }
        }
        return visited;
    }

    public List<Class> disconnectedGraph(Graph graph, Object root) {
        Stack<Object> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Object vertex = stack.pop();
            allVertices.remove(vertex);
            if (!visited.contains(vertex)) {
                visitedDisconnectedVertices.add((Class) vertex);
                for (Vertex v : graph.getAdjVertices(vertex)) {
                    stack.push(v.object);
                }
            }
        }
        return visitedDisconnectedVertices;
    }
}