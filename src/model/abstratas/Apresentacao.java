package model.abstratas;

import model.Artista;
import java.io.Serializable;

public abstract class Apresentacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private static int contadorId = 1;
    private String nomeApresentacao;
    private String data;
    private String hora;
    private int duracaoMinutos;
    private Artista artista;

    public Apresentacao(String nomeApresentacao, String data, String hora, int duracaoMinutos, Artista artista) {
        setNomeApresentacao(nomeApresentacao);
        setData(data);
        setHora(hora);
        setDuracaoMinutos(duracaoMinutos);
        setArtista(artista);
        this.id = contadorId++;
    }

    public static void atualizarContador(int id) {
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public String getNomeApresentacao() {
        return nomeApresentacao;
    }

    public void setNomeApresentacao(String nomeApresentacao) {
        if (nomeApresentacao == null || nomeApresentacao.isEmpty()) {
            throw new IllegalArgumentException("Nome da Apresentação não pode ser vazia.");
        }
        this.nomeApresentacao = nomeApresentacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (data == null || data.isEmpty()){
            throw new IllegalArgumentException("A data da apresentação não pode ser vazia.");
        }
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        if (hora == null || hora.isEmpty()) {
            throw new IllegalArgumentException("Hora da Apresentação não pode ser vazia.");
        }
        this.hora = hora;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        if (duracaoMinutos <= 0) {
            throw new IllegalArgumentException("A duração da Apresentação não pode ser zero ou menor que zero.");
        }
        this.duracaoMinutos = duracaoMinutos;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        if (artista == null) {
            throw new IllegalArgumentException("O artista não pode ser nulo.");
        }
        this.artista = artista;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Apresentacao)) {
            return false;
        }

        Apresentacao outro = (Apresentacao) obj;
        return this.id == outro.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return getTipo() + " | ID: " + id + " | Nome: " + nomeApresentacao + " | Data: " + data + " | Hora: " + hora + " | Duração: " + duracaoMinutos + "min" + " | Artista: " + artista.getNomeArtistico();
    }

    public abstract String getTipo();

    public abstract void exibirDetalhes();
}
