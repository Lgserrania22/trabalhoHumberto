/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgrafos;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Gustavo
 */
public class Prim {
    
    private double pesoTotal = 0.0;
    private LinkedList<ArestaSimples> resultado = new LinkedList();
    private ArrayList<Integer> vertices;
    private double[] chave;
    private int[] pai;
    private Grafo grafo;
    
    public Prim(Grafo grafo, int raiz){
        int u;
        this.grafo = grafo;
        this.chave = new double[this.grafo.getNumeroDeVertices()];
        this.pai = new int[this.grafo.getNumeroDeVertices()];
        this.vertices = new ArrayList(this.grafo.getNumeroDeVertices());
        for(int i = 0; i < this.grafo.getNumeroDeVertices(); i++){
            this.vertices.add(i);
            this.pai[i] = -1;
            this.chave[i] = -1.0;
        }
        this.chave[raiz] = 0;
       while(this.vertices.size() > 0){
           u = extrairMinimo();
           if(u != raiz){
               this.resultado.add(new ArestaSimples(this.pai[u],u,this.chave[u]));
               this.pesoTotal += this.chave[u];
           }
           LinkedList<ArestaSimples> adjacentes = (LinkedList<ArestaSimples>) this.grafo.getAdjacentes(u);
           for(ArestaSimples aresta : adjacentes){
               if((this.vertices.contains(aresta.getDestino())) && (aresta.getPeso() < this.chave[aresta.getDestino()]) && (this.chave[aresta.getDestino()] > 0.0) || (this.chave[aresta.getDestino()] < 0.0)){
                   this.chave[aresta.getDestino()] = aresta.getPeso();
                   this.pai[aresta.getDestino()] = u;
               }
           }
       }
       
       getResultado();
    }
    
    private int extrairMinimo(){
        double peso = this.chave[this.vertices.get(0)];
        int u = this.vertices.get(0);
        for(int i = 1; i < this.vertices.size(); i++){
            if((this.chave[this.vertices.get(i)] <= peso) && (this.chave[this.vertices.get(i)] >= 0.0)){
                u = this.vertices.get(i);
                peso = this.chave[u];
            }else if(peso < 0 && this.chave[this.vertices.get(i)] >= 0){
                u = this.vertices.get(i);
            }
        }
        this.vertices.remove(this.vertices.indexOf(u));
        return u;
    }
    
    public void getResultado(){
        for(ArestaSimples aresta : this.resultado){
            System.out.println("(" + aresta.getOrigem() + "," + aresta.getDestino() + ")");
        }
        System.out.println(this.pesoTotal);
    }
}
