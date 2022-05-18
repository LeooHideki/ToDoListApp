package br.senai.sp.todolistapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Tarefa implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long idTarefa;
    private String titulo;
    private String descricao;
    private long dataCriacao;
    private long DataPrevista;
    private long DataFinalizada;

    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(long dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public long getDataPrevista() {
        return DataPrevista;
    }

    public void setDataPrevista(long dataPrevista) {
        DataPrevista = dataPrevista;
    }

    public long getDataFinalizada() {
        return DataFinalizada;
    }

    public void setDataFinalizada(long dataFinalizada) {
        DataFinalizada = dataFinalizada;
    }
    public boolean isConcluida(){
        return getDataFinalizada() !=0;
    }
    public boolean isAtrasada(){
        return true;
    }
}
