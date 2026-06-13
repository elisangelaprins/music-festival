package model;

import java.io.Serializable;
import model.abstratas.Pessoa;
import model.enums.TipoDocumento;
import model.interfaces.Credenciavel;

public class Staff extends Pessoa implements Credenciavel, Serializable {
    private static final long serialVersionUID = 1L;

    private String cargo;
    private String areaAtuacao;

    public Staff(String nome, TipoDocumento tipoDocumento, String documento, String cargo, String areaAtuacao) {
        super(nome, tipoDocumento, documento);
        setCargo(cargo);
        setAreaAtuacao(areaAtuacao);
    }

    public Staff(int id, String nome, TipoDocumento tipoDocumento, String documento, String cargo, String areaAtuacao) {
        super(id, nome, tipoDocumento, documento);
        setCargo(cargo);
        setAreaAtuacao(areaAtuacao);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (cargo == null || cargo.isEmpty()) {
            throw new IllegalArgumentException("Cargo não pode ser vazio.");
        }
        this.cargo = cargo;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        if (areaAtuacao == null || areaAtuacao.isEmpty()) {
            throw new IllegalArgumentException("Área de atuação não pode ser vazia.");
        }
        this.areaAtuacao = areaAtuacao;
    }

    @Override
    public String getTipo() {
        return "Staff";
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("\n--------------------------------");
        System.out.println(getTipo());
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Documento (" + getTipoDocumento() + "): " + getDocumento());
        System.out.println("Cargo: " + getCargo());
        System.out.println("Área de atuação: " + getAreaAtuacao());
        System.out.println("Credencial: " + gerarCredencial());
    }

    @Override
    public String gerarCredencial() {
        return "STAFF-" + getId() + "-" + cargo.toUpperCase();
    }

    @Override
    public boolean possuiCredencial() {
        return cargo != null && !cargo.isEmpty();
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Cargo: " + getCargo()
                + "\n Área de atuação: " + getAreaAtuacao();
    }
}
