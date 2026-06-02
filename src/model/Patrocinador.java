package model;
import  model.abstratas.Pessoa;
import model.enums.TipoDocumento;
import model.interfaces.RelatorioGeravel;

public class Patrocinador  extends  Pessoa implements RelatorioGeravel{
    private double valorPatrocinio;
    private String categoria;

    public Patrocinador(String nome, TipoDocumento tipoDocumento, String documento, double valorPatrocinio, String categoria) {
        super(nome, tipoDocumento, documento);
        setValorPatrocinio(valorPatrocinio);
        setCategoria(categoria);
    }

    public double getValorPatrocinio() {
        return valorPatrocinio;
    }

    public void setValorPatrocinio(double valorPatrocinio) {
        if (valorPatrocinio <= 0) {
            throw new IllegalArgumentException("Valor do patrocínio inválido");
        }
        this.valorPatrocinio = valorPatrocinio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("Categoria inválida");
        }
        this.categoria = categoria;
    }

    @Override
    public String getTipo() {
        return "Patrocinador" ;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println(this);
    }

    @Override
    public  String gerarRelatorio(){
        return toString();
    }

    @Override
    public void exportarRelatorio(String nomeArquivo) {

    }

    @Override
    public String toString() {
        return super.toString()
                + "\nCategoria: " + categoria
                + "\nValor Patrocinio: R$ " + valorPatrocinio;
    }

}
