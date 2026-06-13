package model.abstratas;

import java.io.Serializable;
import model.enums.TipoDocumento;

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private final TipoDocumento tipoDocumento;
    private final String documento;
    private static int contadorId = 1;

    public Pessoa(String nome, TipoDocumento tipoDocumento, String documento) {
        setNome(nome);
        this.tipoDocumento = validarTipoDocumento(tipoDocumento);
        this.documento = validarDocumento(documento);
        this.id = contadorId++;
    }

    public Pessoa(int id, String nome, TipoDocumento tipoDocumento, String documento) {
        setId(id);
        setNome(nome);
        this.tipoDocumento = validarTipoDocumento(tipoDocumento);
        this.documento = validarDocumento(documento);

        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public static void atualizarContador(int id) {
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
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

    private TipoDocumento validarTipoDocumento(TipoDocumento tipoDocumento) {

        if (tipoDocumento == null) {
            throw new IllegalArgumentException("Tipo de documento não pode ser vazio.");
        }
        return tipoDocumento;
    }

    private String validarDocumento(String documento) {

        tipoDocumento.validar(documento);
        return documento;
    }

    private void setId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        this.id = id;
    }

    public abstract String getTipo();

    public abstract void exibirDetalhes();
}