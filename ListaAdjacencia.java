/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgrafos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author programacao
 */
public class ListaAdjacencia extends Grafo{
    
    private int numeroVertices;
    LinkedList<ArestaSimples>[] lista;
    int cont = 0;
    
    //Variaveis da dfs
    private int tempo = 0;       
    private String[] cor; //Também usado na bfs
    private int[] descoberta;
    private int[] finalizacao;
    private boolean ciclo = false;
    private int verticesFinalizados;
    private Collection<ArestaSimples> arestasArvore = new HashSet<ArestaSimples>();
    private Collection<ArestaSimples> arestasRetorno = new HashSet<ArestaSimples>();
    private Collection<ArestaSimples> arestasAvanco = new HashSet<ArestaSimples>();
    private Collection<ArestaSimples> arestasCross = new HashSet<ArestaSimples>();
    //#############################################################################
    //Variáveis da BFS
    private int[] distancia;
    private int[] pai;
    LinkedList<Integer> fila = new LinkedList();
    //#############################################################################
    
    public ListaAdjacencia(int numeroVertices){
        this.numeroVertices = numeroVertices;
        this.cor = new String[this.numeroVertices];
        this.descoberta = new int[this.numeroVertices];
        this.pai = new int[this.numeroVertices];
        this.finalizacao = new int[this.numeroVertices];
        this.lista = new LinkedList[numeroVertices];
        this.distancia = new int[this.numeroVertices];
        for(int i = 0; i < numeroVertices; i++){
            LinkedList<ArestaSimples> arestas = new LinkedList();
            this.lista[i] = arestas;
        }
    }

    @Override
    public int getNumeroDeVertices() {
        return this.lista.length;
    }

    @Override
    public void addAresta(int origem, int destino) {
        ArestaSimples aresta = new ArestaSimples(origem, destino);
        this.lista[origem].add(aresta);
    }

    @Override
    public void addAresta(int origem, int destino, double peso) {
        ArestaSimples aresta = new ArestaSimples(origem, destino,peso);
        this.lista[origem].add(aresta);
        aresta = new ArestaSimples(destino,origem,peso);
        this.lista[destino].add(aresta);
    }
    
    public void addArestaOrientada(int origem, int destino){
        ArestaSimples aresta = new ArestaSimples(origem, destino);
        this.lista[origem].add(aresta);
    }
    
    public void addArestaOrientada(int origem, int destino, double peso){
        ArestaSimples aresta = new ArestaSimples(origem, destino, peso);
        this.lista[origem].add(aresta);
    }

    @Override
    public void setAresta(int origem, int destino) {
        this.addAresta(origem, destino);
    }

    @Override
    public void setAresta(int origem, int destino, double peso) {
        this.addAresta(origem, destino, peso);
    }

    @Override
    public void removeAresta(int origem, int destino) {
        for(ArestaSimples aresta : lista[origem]){
            if(destino == aresta.getDestino()){
                this.lista[origem].remove(aresta);
            }
        }
    }

    @Override
    public boolean isAdjacente(int origem, int destino) {
        for(ArestaSimples aresta : lista[origem]){
            if(destino == aresta.getDestino()){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ArestaSimples> getAdjacentes(int origem) {
        LinkedList<ArestaSimples> adjacentes = new LinkedList();
        for(ArestaSimples aresta : this.lista[origem]){
            adjacentes.add(aresta);
        }    
        return adjacentes;
    }

    @Override
    public double getPeso(int origem, int destino) {
        for(ArestaSimples aresta : lista[origem]){
            if(destino == aresta.getDestino()){
                return aresta.getPeso();
            }
        }
        
        return 0;
    }

    @Override
    public int addVertice() {
        LinkedList<ArestaSimples>[] novaLista = new LinkedList[numeroVertices + 1];
        for(int i = 0; i < this.numeroVertices; i++){
            novaLista[i] = this.lista[i];
        }
        novaLista[this.numeroVertices] = new LinkedList();
        this.lista = novaLista;
        this.numeroVertices++;
  
        return this.numeroVertices;
    }
    
    private String imprimeLista(){
        String msg = "";
        
        for(int i = 0; i < this.numeroVertices; i++){
            msg += "[" + i + "] - " + this.getAdjacentes(i);
            msg += "\n";
        }
        
        return msg;
    }
    
    @Override
    public void imprime(){
        System.out.println(imprimeLista());
    }
    
    public void preencheLista(){
        long tempoInicial = 0;
        long tempoFinal = 0;
        int cont = 0;
        for(int i = 0; i < this.numeroVertices; i++){
                tempoInicial = System.nanoTime();
                cont++;
                ArestaSimples aresta = new ArestaSimples(0,i);
                this.lista[0].add(aresta);
                if(cont == 10000){
                 tempoFinal = tempoFinal/10000;
                 cont = 0;
                 System.out.println(tempoFinal);
                 tempoFinal = 0;
                }else{
                   tempoFinal += (System.nanoTime() - tempoInicial); 
                }
        }
    }
    
    public void acessarVertices(){
        long tempoInicial = 0;
        long tempoFinal = 0;
        int cont = 0;
        for(int i = 0; i < this.numeroVertices; i++){
                tempoInicial = System.nanoTime();
                cont++;
                if(cont == 10000){
                 tempoFinal = tempoFinal/10000;
                 cont = 0;
                 System.out.println(tempoFinal);
                 tempoFinal = 0;
                }else{
                   tempoFinal += (System.nanoTime() - tempoInicial); 
                }
        }
    }
    
    public void preencheVertice(){
        for (int i = 0; i < 300000; i++){
            ArestaSimples aresta = new ArestaSimples(0,0);
            this.lista[0].add(aresta);
        }
    }
    
    public void acessarArestas(){
        long tempoInicial = 0;
        long tempoFinal = 0;
        int cont = 0;
        this.preencheVertice();
        for(int i = 0; i < 300000; i++){
                tempoInicial = System.nanoTime();
                cont++;
                this.lista[0].get(i);
                if(cont == 10000){
                 tempoFinal = tempoFinal/10000;
                 cont = 0;
                 System.out.println(tempoFinal);
                 tempoFinal = 0;
                }else{
                   tempoFinal += (System.nanoTime() - tempoInicial); 
                }
        }
    }
    
    public void buscaEmProfundidade(int raiz){
        this.verticesFinalizados = 0;
        for(int x = 0; x < this.numeroVertices; x++){
            this.cor[x] = "branco";
        }
        while(this.verticesFinalizados < this.numeroVertices){
            if("branco".equals(this.cor[raiz])){
                dfsVisit(raiz);
            }else{
                for(int i = 0; i < this.numeroVertices; i++){
                    if("branco".equals(this.cor[i])){
                        raiz = i;
                        break;
                    }
                }
            }
        }
    }
    
    public void dfsVisit(int u){
        boolean fimLista = false;
        double peso;
        Vertice v;
        int apontador = 0;
        this.cor[u] = "cinza";
        this.descoberta[u] = this.tempo;
        this.tempo++;
        //System.out.println("Vértice visitado:" + u + "\nTempo de descoberta: " + this.descoberta[u] + "\nCor: " + this.cor[u]);
        if(this.lista[u].size() > 0){
            for(int i = 0; i < this.lista[u].size(); i++){
                if("branco".equals(this.cor[this.lista[u].get(i).getDestino()])){
                    dfsVisit(this.lista[u].get(i).getDestino());
                    this.lista[u].get(i).setTipo("arvore");
                    this.arestasArvore.add(this.lista[u].get(i));
                }else if("cinza".equals(this.cor[this.lista[u].get(i).getDestino()])){
                    this.lista[u].get(i).setTipo("retorno");
                    this.arestasRetorno.add(this.lista[u].get(i));
                    this.ciclo = true;
                }else{
                    if(this.descoberta[u] < this.finalizacao[this.lista[u].get(i).getDestino()]){
                        this.lista[u].get(i).setTipo("avanço");
                        this.arestasAvanco.add(this.lista[u].get(i));
                    }else{
                        this.lista[u].get(i).setTipo("cross");
                        this.arestasCross.add(this.lista[u].get(i));
                    }
                }
            }
        }
        this.cor[u] = "preto";
        this.finalizacao[u] = this.tempo;
        this.tempo++;
        this.verticesFinalizados++;
        //System.out.println("Vértice finalizado: " + u + "\nTempo de finalização: "+ this.finalizacao[u] +"\nCor: " + this.cor[u]);  
    }
    
    public Collection<ArestaSimples> arestasDeRetorno(){     
        return this.arestasRetorno;
    }

    public Collection<ArestaSimples> arestasDeAvanco(){        
        return this.arestasAvanco;
    }

    public Collection<ArestaSimples> arestasDeCruzamento(){       
        return this.arestasCross;
    }

    public Collection<ArestaSimples> arestasDeArvore(){
       return this.arestasArvore;
    }

    public boolean existeCiclo(){
        return this.ciclo;
    }
    
    public void buscaEmLargura(int raiz){
        int u;
        for(int i = 0; i < this.numeroVertices; i++){
            this.cor[i] = "branco";
            this.distancia[i] = -1;
            this.pai[i] = -1;
        }
        this.cor[raiz] = "cinza";
        this.distancia[raiz] = 0;
        System.out.println("Visitou o vértice: " + raiz + "\nCor: " + this.cor[raiz] +"\nDistância: " + this.distancia[raiz] + "\nSeu pai: " + this.pai[raiz]);
        enfileira(raiz);
        while(this.fila.size() > 0){
            u = desenfileira();
            for(int j = 0; j < this.lista[u].size(); j++){
                int vertice = this.lista[u].get(j).getDestino();
                if("branco".equals(this.cor[vertice])){
                    this.cor[vertice] = "cinza";
                    this.distancia[vertice] = this.distancia[u] + 1;
                    this.pai[vertice] = u;
                    System.out.println("Visitou o vértice: " + vertice + "\nCor: " + this.cor[vertice] +"\nDistância: " + this.distancia[vertice] + "\nSeu Pai: " + this.pai[vertice]);
                    enfileira(vertice);
                }
            }
            System.out.println("Vértice finalizado: " + u);
            this.cor[u] = "preto";
        }
    }
    
    private void enfileira(int u){
        this.fila.add(u);
    }
    
    private int desenfileira(){
        int u = this.fila.get(0);
        this.fila.remove(0);
        return u;
    }
    
    public String paisEDistancias(){
        String texto = "";
        for(int i = 0; i < this.numeroVertices; i++){
            texto += "Vértice: " + i + " | Pai: " + this.pai[i] + " | Distância: " + this.distancia[i] + "\n";
        }
        return texto;
    }
}
