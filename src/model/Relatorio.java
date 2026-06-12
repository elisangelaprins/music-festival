package model;
import java.io.Serializable;
import model.interfaces.RelatorioGeravel;
public class Relatorio implements RelatorioGeravel, Serializable{
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String conteudo;

    public Relatorio (String titulo, String conteudo) {
        setTitulo(titulo);
        setConteudo(conteudo);
    }

    @Override
    public String gerarRelatorioGeral(){
        return this.conteudo;
    }

    @Override
    public void exportarRelatorio() {
        System.out.println("Exportação acionado para o relatório: " + this.titulo);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Título inválido");
        }
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        if (conteudo == null || conteudo.isEmpty()) {
            throw new IllegalArgumentException("Conteúdo invalido");
        }
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "Título: " + titulo
                + "\nConteúdo: " + conteudo;
    }
}
