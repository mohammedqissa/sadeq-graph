package com.akuma.proj3.graph;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> implements Serializable,Cloneable{

    public List<Edge<T>> allEdges;
    public Map<Long, Vertex<T>> allVertex;
   public  boolean isDirected = false;
   public boolean isWeighted= false;
   public int lastId = 0;

    public Graph(boolean isDirected) {
        allEdges = new ArrayList<Edge<T>>();
        allVertex = new HashMap<Long, Vertex<T>>();
        this.isDirected = isDirected;
    }

    private Graph() {
    }

    public void addEdge(long id1, long id2, Line line) {
        addEdge(id1, id2, 0, line);
    }
        public void setAllEdges(List<Edge<T>> allEdges) {
        this.allEdges = allEdges;
    }
    

    //This works only for directed graph because for undirected graph we can end up
    //adding edges two times to allEdges
    public void addVertex(Vertex<T> vertex) {
        if (allVertex.containsKey(vertex.getId())) {
            return;
        }
        lastId++;
        allVertex.put(vertex.getId(), vertex);
        for (Edge<T> edge : vertex.getEdges()) {
            allEdges.add(edge);
        }
    }

    public Vertex<T> addSingleVertex(long id, Point p) {
        if (allVertex.containsKey(id)) {
            return allVertex.get(id);
        }
        Vertex<T> v = new Vertex<T>(id);
        v.setLocation(p);
        lastId++;
        allVertex.put(id, v);
        return v;
    }

    public Vertex<T> getVertex(long id) {
        return allVertex.get(id);
    }

    public Vertex<T> deleteVertex(long id) {
        Vertex vToRemove = allVertex.get(id);
        if (vToRemove == null) {
            return null;
        }
      //  debug(this.toString());
//           debug("all vertex (before del) : " + allVertex.toString());
        allVertex.remove(id);
        for (int i = 0; i < allEdges.size(); i++) {
            Edge edge = allEdges.get(i);

            if (edge.getVertex1().getId() == id
                    || edge.getVertex2().getId() == id) {
                allEdges.remove(edge);
                i--;
            }

        }

//        debug("Deleted Vertex Of ID : " + id);
//        debug("all vertex (after del) : " + allVertex.toString());
        return vToRemove;
    }

    public void addEdge(long id1, long id2, int weight, Line line) {
        Vertex<T> vertex1 = null;
        if (allVertex.containsKey(id1)) {
            vertex1 = allVertex.get(id1);
        } else {
            vertex1 = new Vertex<T>(id1);
            allVertex.put(id1, vertex1);
        }
        Vertex<T> vertex2 = null;
        if (allVertex.containsKey(id2)) {
            vertex2 = allVertex.get(id2);
        } else {
            vertex2 = new Vertex<T>(id2);
            allVertex.put(id2, vertex2);
        }

        Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight, line);
        allEdges.add(edge);
        vertex1.addAdjacentVertex(edge, vertex2);
        if (!isDirected) {
            vertex2.addAdjacentVertex(edge, vertex1);
        }

    }

    public List<Edge<T>> getAllEdges() {
        return allEdges;
    }

    public Collection<Vertex<T>> getAllVertex() {
        return allVertex.values();
    }

    public Map<Long, Vertex<T>> getAllVertexMap() {
        return allVertex;
    }

    public void setDataForVertex(long id, T data) {
        if (allVertex.containsKey(id)) {
            Vertex<T> vertex = allVertex.get(id);
            vertex.setData(data);
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Edge<T> edge : getAllEdges()) {
            buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
            buffer.append("\n");
        }
        return buffer.toString();
    }

    private void debug(String string) {
        System.err.println("[DEBUG] : " + this.getClass().getName() + " => [MESSAGE] : " + string);
    }

    public boolean isIsDirected() {
        return isDirected;
    }

    public boolean isIsWeighted() {
        return isWeighted;
    }

    public int getLastId() {
        return lastId;
    }
    
    
    @Override
    public Graph<T> clone(){
        Graph<T> clone = new Graph<T>();
        clone.allEdges = this.getAllEdges();
        clone.allVertex = this.getAllVertexMap();
        clone.isDirected = this.isIsDirected();
        clone.isWeighted = this.isIsWeighted();
        clone.lastId = this.getLastId();
        return clone;
    }
}
