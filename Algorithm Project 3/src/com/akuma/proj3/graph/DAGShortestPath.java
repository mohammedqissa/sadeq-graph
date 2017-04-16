package com.akuma.proj3.graph;

import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAGShortestPath<T> {

    Map<Vertex<T>, Vertex<T>> parent = new HashMap<>();

    ;

    public Map<Vertex<T>, Integer> shortestPath(Graph<T> graph, Vertex<T> startVertex, List<Vertex<T>> deletedVertexes) {

        parent.put(startVertex, null);
        Map<Vertex<T>, Integer> distance = new HashMap<Vertex<T>, Integer>();
        TopologicalSort<T> sort = new TopologicalSort<T>();
        Deque<Vertex<T>> deque = sort.topSort(graph);
        distance.put(startVertex, 0);
        while (!deque.isEmpty()) {
            Vertex<T> vertex = deque.poll();
            for (Edge<T> edge : vertex.getEdges()) {
                if (getDistance(edge.getVertex2(), distance) > getDistance(edge.getVertex1(), distance) + edge.getWeight()) {
                    if (deletedVertexes.contains(edge.getVertex1()) || deletedVertexes.contains(edge.getVertex2())) {
                        continue;
                    }
                    distance.put(edge.getVertex2(), getDistance(edge.getVertex1(), distance) + edge.getWeight());
                    
                    parent.put(edge.getVertex2(), edge.getVertex1());
                }
            }
        }

        return distance;
    }

    private int getDistance(Vertex<T> vertex, Map<Vertex<T>, Integer> distance) {
        return distance.containsKey(vertex) ? distance.get(vertex) : 1000;
    }

    public Map<Vertex<T>, Vertex<T>> getParent() {
        return parent;
    }

    public void setParent(Map<Vertex<T>, Vertex<T>> parent) {
        this.parent = parent;
    }

}
