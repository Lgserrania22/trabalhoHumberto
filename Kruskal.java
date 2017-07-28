/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgrafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public class Kruskal {
    
    private double pesoTotal = 0.0;
    private LinkedList<ArestaSimples> resultado = new LinkedList();
    private LinkedList<Integer>[] conjuntoVertices;
    private Grafo grafo;
    private ArrayList<ArestaSimples> conjuntoArestas = new ArrayList();
    private int numeroVertices;
    
    public Kruskal(Grafo grafo){
        this.grafo = grafo;
        this.numeroVertices = this.grafo.getNumeroDeVertices();
        this.conjuntoVertices = new LinkedList[this.numeroVertices];
        for(int i = 0; i < this.numeroVertices; i++){
            this.conjuntoVertices[i] = new LinkedList();
            this.conjuntoVertices[i].add(i);
            List<ArestaSimples> adjacentes = this.grafo.getAdjacentes(i);
            for(ArestaSimples aresta : adjacentes){
                this.conjuntoArestas.add(aresta);
            }
        }   
        Collections.sort(this.conjuntoArestas, (ArestaSimples a1, ArestaSimples a2) -> Double.valueOf(a1.getPeso()).compareTo(a2.getPeso()));
        for(ArestaSimples aresta : this.conjuntoArestas){
            if(!this.verificaConjunto(aresta.getOrigem(), aresta.getDestino())){
                resultado.add(aresta);
                this.pesoTotal += aresta.getPeso();
                this.aplicaUniao(aresta.getOrigem(), aresta.getDestino());
            }
        }
    }
    
    private int numeroArestas(Grafo g){
        int numeroArestas = 0;
        for(int i = 0; i < g.getNumeroDeVertices(); i++){
            List<ArestaSimples> arestas = this.grafo.getAdjacentes(i);
            for(ArestaSimples aresta : arestas){
                this.conjuntoArestas.add(aresta);
                numeroArestas++;
            }
        }
        return numeroArestas;
    }
    
    private boolean verificaConjunto(int u, int v){
        for(int i = 0; i < this.numeroVertices; i++){
            if(this.conjuntoVertices[i].contains(u)){
                if(this.conjuntoVertices[i].contains(v)){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    
    private void aplicaUniao(int u, int v){
        int origem = -1;
        int destino = -1;
        for(int i = 0; i < this.numeroVertices; i++){
            if(this.conjuntoVertices[i].contains(u)){
                origem = i;
            }
            if(this.conjuntoVertices[i].contains(v)){
                destino = i;
            }
            if((origem != -1) && (destino != -1)){
                int vertice;
                while(this.conjuntoVertices[origem].size() > 0){
                    vertice = this.conjuntoVertices[origem].pollFirst();
                    this.conjuntoVertices[destino].add(vertice);
                }
            }
        }
    }
    
    public LinkedList getResultado(){
        return this.resultado;
    }
    
    public double getPeso(){
        return this.pesoTotal;
    }
    
}
