package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Operadora {
    private int registroOperadora;
    private String nomeFantasia;
    private float totalDespesas;

    public Operadora(int registroOperadora, String nomeFantasia) {
        this.registroOperadora = registroOperadora;
        this.nomeFantasia = nomeFantasia;
        this.totalDespesas = 0; // Inicializa com 0
    }

    public void somarDespesas(float despesas) {
        this.totalDespesas += despesas; // Soma as despesas de todas as ocorrências
    }

    public float getTotalDespesas() {
        return totalDespesas;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    @Override
    public String toString() {
        return "Operadora{" +
                "registroOperadora=" + registroOperadora +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", totalDespesas=" + totalDespesas +
                '}';
    }
}