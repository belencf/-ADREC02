/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.directorios;

import java.util.List;

/**
 *
 * @author Belén
 */
public class Continente {
    protected String nome;
    protected int numero_casos;
    protected int numero_falecementos;

    Continente(String nombre){
        this.nome=nombre;
        this.numero_casos=0;
        this.numero_falecementos=0;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the numero_casos
     */
    public int getNumero_casos() {
        return numero_casos;
    }

    /**
     * @param numero_casos the numero_casos to set
     */
    public void setNumero_casos(int numero_casos) {
        this.numero_casos = numero_casos;
    }

    /**
     * @return the numero_falecementos
     */
    public int getNumero_falecementos() {
        return numero_falecementos;
    }

    /**
     * @param numero_falecementos the numero_falecementos to set
     */
    public void setNumero_falecementos(int numero_falecementos) {
        this.numero_falecementos = numero_falecementos;
    }
    
}
