/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akuma.proj3.graph;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

 public class Vertex<T> implements Serializable{
    long id;
    private T data;
    private List<Edge<T>> edges = new ArrayList<Edge<T>>();
    private List<Vertex<T>> adjacentVertex = new ArrayList<Vertex<T>>();
    private Point location;
    
    
    
   public Vertex(long id,Point location){
        this.id = id;
        this.location = location;
    }
   
   public Vertex(long id){
        this.id = id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setId(long id) {
        this.id = id;
    }
    
   
    public long getId(){
        return id;
    }
    
    public void setData(T data){
        this.data = data;
    }
    
    public T getData(){
        return data;
    }
    
    public void addAdjacentVertex(Edge<T> e, Vertex<T> v){
        edges.add(e);
        adjacentVertex.add(v);
    }

    @Override
    public String toString() {
      return String.valueOf(id);//  return id+"";
    }
    
    public void removeAdjacentVertex(Vertex<T> v){
    }
  
    
    public List<Vertex<T>> getAdjacentVertexes(){
        return adjacentVertex;
    }
    
    public List<Edge<T>> getEdges(){
        return edges;
    }
    
    public int getDegree(){
        return edges.size();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id != other.id)
            return false;
        return true;
    }
}