package org.example.Graph;

import java.util.Objects;

public class Vertex {
    Object object;

    Vertex(Object object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(object, vertex.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object);
    }
}