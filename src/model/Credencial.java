package model;

import java.io.Serializable;
import model.abstratas.Pessoa;
import model.enums.TipoAcesso;
import model.interfaces.Credenciavel;

public class Credencial implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private static int contadorId = 1;
    private String codigo;
    private TipoAcesso tipoAcesso;
    private Credenciavel titular;

    public Credencial(String codigo, TipoAcesso tipoAcesso, Credenciavel titular) {
        setCodigo(codigo);
        setTipoAcesso(tipoAcesso);
        setTitular(titular);
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("Código da credencial não pode ser vazio.");
        }
        this.codigo = codigo;
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        if (tipoAcesso == null) {
            throw new IllegalArgumentException("Tipo de acesso não pode ser vazio.");
        }
        this.tipoAcesso = tipoAcesso;
    }

    public Credenciavel getTitular() {
        return titular;
    }

    private void setTitular(Credenciavel titular) {
        if (titular == null) {
            throw new IllegalArgumentException("Credencial precisa estar vinculada a um titular credenciável.");
        }
        this.titular = titular;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Credencial)) {
            return false;
        }
        Credencial outro = (Credencial) obj;
        return codigo.equals(outro.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return "Credencial | ID: " + id
                + " | Código: " + codigo
                + " | Acesso: " + tipoAcesso
                + " | Titular: " + (titular instanceof Pessoa ? ((Pessoa) titular).getNome() : "N/A");
    }
}