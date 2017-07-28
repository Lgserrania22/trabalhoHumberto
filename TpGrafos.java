/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgrafos;

import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
/**
 *
 * @author Gustavo
 */
public class TpGrafos {

    /**
     * @param args the command line arguments
     */
    private Scanner in;
    private String tipoRepresentacao;
    private int numeroVertices;
    private String busca;
    private Grafo grafo;
    private int raiz;
    
    public TpGrafos(Scanner in, String tipoRepresentacao, int numeroVertices, String busca, int raiz){
        this.in = in;
        this.tipoRepresentacao = tipoRepresentacao;
        this.numeroVertices = numeroVertices;
        this.busca = busca;
        this.raiz = raiz;
        
        carregaGrafo(this.in, this.numeroVertices, this.tipoRepresentacao);
        if("Busca em Profundidade".equals(this.busca)){
            buscaEmProfundidade();
        }else{
            buscaEmLargura();
        }
    }
    
    public TpGrafos(Scanner in, String tipoRepresentacao, int numeroVertices, String algoritmo){
        this.in = in;
        this.tipoRepresentacao = tipoRepresentacao;
        this.numeroVertices = numeroVertices;
        carregaGrafo(this.in, this.numeroVertices, this.tipoRepresentacao);
        if("Kruskal".equals(algoritmo)){
            kruskal();
        }else{
            prim();
        }
    }
    
    private void carregaGrafo(Scanner in, int numeroVertices, String tipoRepresentacao){
        int origem;
        String line;
        String[] partes;
        String[] aresta;
        if("Lista de Adjacência".equals(tipoRepresentacao)){
            grafo = new ListaAdjacencia(numeroVertices);
        }else{
            grafo = new MatrizDeAdjacencia(numeroVertices);
        }
        while(in.hasNext()){
            line = in.nextLine();
            partes = line.split("; |\\ |\\;");
            origem = Integer.parseInt(partes[0]);
            for(int i = 1; i < partes.length; i++){
                aresta = partes[i].split("-");
                grafo.addArestaOrientada(origem, Integer.parseInt(aresta[0]), Double.parseDouble(aresta[1]));
            }
        }
    }
    
    public void mostraGrafo(){
        this.grafo.imprime();
    }
    
    public void buscaEmProfundidade(){
        this.grafo.buscaEmProfundidade(this.raiz);
        StringBuilder sb = new StringBuilder();
        sb.append(this.arestasDeRetorno());
        sb.append(this.arestasDeAvanco());
        sb.append(this.arestasDeCruzamento());
        sb.append(this.arestasDeArvore());
        sb.append(this.existeCiclo());
        System.out.println(sb.toString());
    }
    
    public String arestasDeRetorno(){
        Collection<ArestaSimples> arestasRetorno = this.grafo.arestasDeRetorno();
        StringBuilder sb = new StringBuilder();
        sb.append("\nArestas de retorno:");
        for(ArestaSimples aresta: arestasRetorno){
            sb.append(" [" + aresta.getOrigem() + " , " + aresta.getDestino() + "]");
        }
        return sb.toString();
    }

    
    public String arestasDeAvanco(){
        Collection<ArestaSimples> arestasAvanco = this.grafo.arestasDeAvanco();
        StringBuilder sb = new StringBuilder();
        sb.append("\nArestas de Avanço:");
        for(ArestaSimples aresta: arestasAvanco){
            sb.append(" [" + aresta.getOrigem() + " , " + aresta.getDestino() + "]");
        }
        return sb.toString();
    }

    
    public String arestasDeCruzamento(){
        Collection<ArestaSimples> arestasCruzamento = this.grafo.arestasDeCruzamento();
        StringBuilder sb = new StringBuilder();
        sb.append("\nArestas de Cruzamento:");
        for(ArestaSimples aresta: arestasCruzamento){
            sb.append(" [" + aresta.getOrigem() + " , " + aresta.getDestino() + "]");
        }
        return sb.toString();
    }

    
    public String arestasDeArvore(){
        Collection<ArestaSimples> arestasArvore = this.grafo.arestasDeArvore();
        StringBuilder sb = new StringBuilder();
        sb.append("\nArestas de Arvore:");
        for(ArestaSimples aresta: arestasArvore){
            sb.append(" [" + aresta.getOrigem() + " , " + aresta.getDestino() + "]");
        }
        return sb.toString();
    }

    public String existeCiclo(){
        if(this.grafo.existeCiclo()){
            return "Existe ciclo!";
        }else{
            return "Não existe ciclo!";
        }
    }
    
    private void buscaEmLargura(){
        this.grafo.buscaEmLargura(this.raiz);
    }
    
    public String paisEDistancias(){
        return this.grafo.paisEDistancias();
    }
    
    private void kruskal(){
        Kruskal k = new Kruskal(this.grafo);
        LinkedList<ArestaSimples> arestas = k.getResultado();
        for(ArestaSimples aresta : arestas){
            System.out.println("(" + aresta.getOrigem() + "," + aresta.getDestino() + ")");
        }
        System.out.println(k.getPeso());
    }
    
    private void prim(){
        Prim p = new Prim(this.grafo, 0);
    }
    
}
