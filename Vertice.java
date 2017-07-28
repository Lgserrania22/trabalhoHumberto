/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgrafos;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gustavo
 */
//Classe criada para conseguir listar de forma eficiente os adjacentes de um vértice para a lista de adjacência
public class Vertice {
    
    private int vertice;
    private List<Integer> adjacentes = new LinkedList<>();
    private List<Double> pesos = new LinkedList<>();
    
    public Vertice(int vertice){
        this.vertice = vertice;
    }
    
    public void setAdjacente(int verticeAdj){
        if(!verificaAdjacencia(verticeAdj)){
            adjacentes.add(verticeAdj);
            pesos.add(0.0);
        }
    }
    
    public void setAdjacente(int verticeAdj, double peso){
        if(!verificaAdjacencia(verticeAdj)){
            adjacentes.add(verticeAdj);
            pesos.add(peso);
        }
    }
    
    public void removeAdjacente(int verticeAdj){
        if(!verificaAdjacencia(verticeAdj)){
            int index = adjacentes.indexOf(verticeAdj);
            adjacentes.remove(verticeAdj);
            pesos.remove(index);
        }
    }
    
    public List getAjacentes(){
        return adjacentes;
    }
    
    public double getPeso(int verticeAdj){
        int index = adjacentes.indexOf(verticeAdj);
        return pesos.get(index);
    }
    
    public boolean verificaAdjacencia(int destino){
        return adjacentes.contains(destino);
    }
    
    public int getNumeroAdjacentes(){
        return adjacentes.size();
    }
    
    public int getAdjacente(int index){
        return adjacentes.get(index);
    }
    
    public int getVertice(){
        return this.vertice;
    }
}
