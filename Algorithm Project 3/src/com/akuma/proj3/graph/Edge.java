/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akuma.proj3.graph;

import java.io.Serializable;

public class Edge<T> implements Serializable{

    private boolean isDirected = false;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private int weight;
    private Line line = new Line();

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, Line line) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.line = line;

    }

    public void setVertex1(Vertex<T> vertex1) {
        this.vertex1 = vertex1;
    }

    public void setVertex2(Vertex<T> vertex2) {
        this.vertex2 = vertex2;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    
    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected, int weight, Line line) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
        this.isDirected = isDirected;
        this.line = line;
    }

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }

    public boolean isIsDirected() {
        return isDirected;
    }

    public void setIsDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Vertex<T> getVertex1() {
        return vertex1;
    }

    public Vertex<T> getVertex2() {
        return vertex2;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
        result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Edge other = (Edge) obj;
        if (vertex1 == null) {
            if (other.vertex1 != null) {
                return false;
            }
        } else if (!vertex1.equals(other.vertex1)) {
            return false;
        }
        if (vertex2 == null) {
            if (other.vertex2 != null) {
                return false;
            }
        } else if (!vertex2.equals(other.vertex2)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
                + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
    }
}
