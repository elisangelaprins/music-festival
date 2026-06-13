package model;

import java.io.Serializable;
import model.abstratas.Pessoa;
import model.interfaces.Credenciavel;

public class Credencial implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private static int contadorId = 1;
    private String codigo;
    private String tipoAcesso;
    private Credenciavel titular;

    public Credencial(String codigo, String tipoAcesso, Credenciavel titular) {
        setCodigo(codigo);
        setTipoAcesso(tipoAcesso);
        setTitular(titular);
        this.id = contadorId++;
    }

    public Credencial(int id, String codigo, String tipoAcesso, Credenciavel titular) {
        setId(id);
        setCodigo(codigo);
        setTipoAcesso(tipoAcesso);
        setTitular(titular);

        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        this.id = id;
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

    public String getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(String tipoAcesso) {
        if (tipoAcesso == null || tipoAcesso.isEmpty()) {
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