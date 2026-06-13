package model;

import model.abstratas.Pessoa;
import model.enums.TipoDocumento;

public class Visitante extends Pessoa {
    String email;
    String telefone;
    public Visitante(String nome, TipoDocumento tipoDocumento, String documento, String email, String telefone) {
        super(nome, tipoDocumento, documento);
        setEmail(email);
        setTelefone(telefone);
    }

    public void setEmail(String email) {
        if (!validarEmail(email)) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.email = email;
    }
    public void setTelefone(String telefone) {
        if (telefone.length() < 10 || telefone.length() > 11) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.telefone = telefone;
    }

    public String getEmail() {return email;}
    public String getTelefone() {return telefone;}

    public boolean validarEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    @Override
    public String getTipo() {
        return "Visitante";
    }
    @Override
    public void exibirDetalhes() {
        System.out.println("\n--------------------------------");
        System.out.println(getTipo());
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Documento (" +getTipoDocumento() + "): " + getDocumento());
        System.out.println("Email: " + getEmail());
        System.out.println("Telefone: " + getTelefone());
    }
    @Override
    public String toString() {
        return super.toString()
                +" | Tipo: " + getTipo()
                +" | E-mail: " + getEmail()
                +" | Telefone: " +getTelefone();
    }
}
