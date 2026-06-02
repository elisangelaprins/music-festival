package model.abstratas;

import java.io.Serializable;
import model.enums.TipoDocumento;

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private TipoDocumento tipoDocumento;
    private String documento;
    private static int contadorId = 1;

    public Pessoa(String nome, TipoDocumento tipoDocumento, String documento) {
        this.id = contadorId++;
        setNome(nome);
        setTipoDocumento(tipoDocumento);
        setDocumento(documento);
    }

    public Pessoa(int id, String nome, TipoDocumento tipoDocumento, String documento) {
        setId(id);
        setNome(nome);
        setTipoDocumento(tipoDocumento);
        setDocumento(documento);

        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        if(documento == null || documento.isEmpty()) {
            throw new IllegalArgumentException("Documento não pode ser vazio.");
        }

        if(this.tipoDocumento != null && this.tipoDocumento == TipoDocumento.CPF && documento.length() != 11){
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos.");
        }

        if (this.tipoDocumento != null && this.tipoDocumento == TipoDocumento.PASSAPORTE && documento.length() != 8) {
            throw new IllegalArgumentException("Passaporte deve conter exatamente 8 caracteres.");
        }

        this.documento = documento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        if (tipoDocumento == null) {
            throw new IllegalArgumentException("Tipo de documento não pode ser vazio.");
        }
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if(!(obj instanceof Pessoa)) {
            return false;
        }

        Pessoa outro = (Pessoa) obj;
        return documento.equals(outro.documento);
    }

    @Override
    public int hashCode() {
        return documento.hashCode();
    }

    @Override
    public String toString() {
        return getTipo() + " | ID: " + id + " | Nome: " + nome + "\n" + tipoDocumento.name() +": " + documento;
    }

    private void setId(int id){
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        this.id = id;
    }

    public abstract String getTipo();

    public abstract void exibirDetalhes();
}