package model;
import model.enums.StatusProposta;
import java.io.Serializable;
public class Proposta implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int contadorId = 1;

    private int id;
    private String titulo;
    private String descricao;
    private double valorProposto;
    private StatusProposta status;
    private Patrocinador patrocinador;

    public Proposta (String titulo, String descricao, double valorProposto, StatusProposta status, Patrocinador patrocinador) {
        this.id = contadorId++;
        setTitulo(titulo);
        setDescricao(descricao);
        setValorProposto(valorProposto);
        setStatus(status);
        setPatrocinador(patrocinador);
    }

    public static void atualizarContador(int maiorId) {
        if (maiorId >= contadorId) {
            contadorId = maiorId + 1;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorProposto() {
        return valorProposto;
    }
    public StatusProposta getStatus() {
        return status;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título da proposta não pode ser vazio!");
        }
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição proposta não pode ser vazia! ");
        }
        this.descricao = descricao;
    }

    public void setValorProposto(double valorProposto) {
        if (valorProposto <= 0) {
            throw new IllegalArgumentException("O valor proposto deve ser maior que zero");
        }
        this.valorProposto = valorProposto;
    }

    public void setStatus (StatusProposta status) {
        if (status == null) {
            throw new IllegalArgumentException("O status da proposta não pode ser nulo! ");
        }
        this.status = status;
    }
    public void setPatrocinador(Patrocinador patrocinador) {
       if (patrocinador == null) {
           throw new IllegalArgumentException(" A proposta deve obrigatoriamente possuir um Patrocinador válido! ");
       }
        this.patrocinador = patrocinador;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               "| Título: " + titulo +
               "| Status: " + status +
               "| Valor: R$ " + valorProposto +
               "| Patrocinador: " + patrocinador.getNome();
    }

}
