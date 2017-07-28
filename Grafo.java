/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgrafos;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author programacao
 */
public abstract class Grafo {
    public abstract int getNumeroDeVertices();

    public abstract void addAresta(int origem, int destino);

    public abstract void addAresta(int origem, int destino, double peso);
    
    public abstract void addArestaOrientada(int origem, int destino);
    
    public abstract void addArestaOrientada(int origem, int destino, double peso);

    public abstract void setAresta(int origem, int destino);

    public abstract void setAresta(int origem, int destino, double peso);

    public abstract void removeAresta(int origem, int destino);

    public abstract boolean isAdjacente(int origem, int destino);

    public abstract List<ArestaSimples> getAdjacentes(int vertice);

    public abstract double getPeso(int origem, int destino);
    
    public abstract int addVertice();
    
    public abstract void buscaEmProfundidade(int raiz);
    
    public abstract void dfsVisit(int u);
    
    public abstract void imprime();
    
    public abstract Collection<ArestaSimples> arestasDeAvanco();
    
    public abstract Collection<ArestaSimples> arestasDeArvore();
    
    public abstract Collection<ArestaSimples> arestasDeCruzamento();
    
    public abstract Collection<ArestaSimples> arestasDeRetorno();
    
    public abstract boolean existeCiclo();
    
    public abstract void buscaEmLargura(int raiz);
    
    public abstract String paisEDistancias();
}
