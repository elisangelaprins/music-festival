package model;
import java.io.Serializable;
import model.interfaces.RelatorioGeravel;

public class Patrocinador  implements RelatorioGeravel, Serializable{
   private static final long serialVersionUID = 1L;

   private  static int contadorId = 1;

   private int id;
   private String nome;
   private String cnpj;
   private double valorPatrocinio;
   private String categoria;


    public Patrocinador(String nome, String cnpj, double valorPatrocinio, String categoria) {
        this.id = contadorId++;

        setNome(nome);
        setCnpj(cnpj);
        setValorPatrocinio(valorPatrocinio);
        setCategoria(categoria);

    }

    public Patrocinador(int id, String nome, String cnpj, double valorPatrocinio, String categoria) {
        this.id = id;
        setNome(nome); setCnpj(cnpj);
        setValorPatrocinio(valorPatrocinio);
        setCategoria(categoria);

        if (id >= contadorId) {

            contadorId = id + 1; }
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public double getValorPatrocinio() {
        return valorPatrocinio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        this.cnpj = cnpj;
    }

    public void setValorPatrocinio(double valorPatrocinio) {
        if (valorPatrocinio <= 0) {
            throw new IllegalArgumentException("Valor do patrocínio inválido");
        }
        this.valorPatrocinio = valorPatrocinio;
    }



    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("Categoria inválida");
        }
        this.categoria = categoria;
    }

    @Override
    public String gerarRelatorio() {
        return toString();
    }

    @Override
    public void exportarRelatorio() {

    }


    @Override
    public String toString() {
        return "ID: " + id
                + "\nNome: " + nome
                + "\nCNPJ: " + cnpj
                + "\nCategoria: " + categoria
                + "\nValor Patrocinio: R$ " + valorPatrocinio;
    }

}
