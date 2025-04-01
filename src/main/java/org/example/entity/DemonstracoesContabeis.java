package org.example.entity;

import javax.xml.crypto.Data;

public class DemonstracoesContabeis {

    private long id;
    private Data data;
    private int reg_ans;
    private int cd_conta_contabil;
    private String descricao;
    private float vl_saldo_inicial;
    private float vl_saldo_final;

    public DemonstracoesContabeis() {
    }

    public DemonstracoesContabeis(long id, Data data, int reg_ans, int cd_conta_contabil, String descricao, float vl_saldo_inicial, float vl_saldo_final) {
        this.id = id;
        this.data = data;
        this.reg_ans = reg_ans;
        this.cd_conta_contabil = cd_conta_contabil;
        this.descricao = descricao;
        this.vl_saldo_inicial = vl_saldo_inicial;
        this.vl_saldo_final = vl_saldo_final;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getReg_ans() {
        return reg_ans;
    }

    public void setReg_ans(int reg_ans) {
        this.reg_ans = reg_ans;
    }

    public int getCd_conta_contabil() {
        return cd_conta_contabil;
    }

    public void setCd_conta_contabil(int cd_conta_contabil) {
        this.cd_conta_contabil = cd_conta_contabil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getVl_saldo_inicial() {
        return vl_saldo_inicial;
    }

    public void setVl_saldo_inicial(float vl_saldo_inicial) {
        this.vl_saldo_inicial = vl_saldo_inicial;
    }

    public float getVl_saldo_final() {
        return vl_saldo_final;
    }

    public void setVl_saldo_final(float vl_saldo_final) {
        this.vl_saldo_final = vl_saldo_final;
    }


}
