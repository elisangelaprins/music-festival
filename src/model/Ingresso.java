package model;

import model.abstratas.Apresentacao;
import model.enums.TipoIngresso;

import java.io.Serializable;

public class Ingresso implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private TipoIngresso tipoIngresso;
    private Visitante visitante;
    private Apresentacao apresentacao;
    private double valor;
    private double valorFinal;
    private static int contadorId = 1;
    private String rgm;

    public Ingresso(TipoIngresso tipoIngresso, Visitante visitante, Apresentacao apresentacao, double valor, double valorFinal) {
        setTipoIngresso(tipoIngresso);
        setVisitante(visitante);
        setApresentacao(apresentacao);
        setValor(valor);
        setValorFinal(valorFinal);
        setRgm("Sem relevância.");
        this.id = contadorId++;
    }
    public Ingresso(TipoIngresso tipoIngresso, Visitante visitante, Apresentacao apresentacao, double valor, double valorFinal, String rgm) {
        setTipoIngresso(tipoIngresso);
        setVisitante(visitante);
        setApresentacao(apresentacao);
        setValor(valor);
        setValorFinal(valorFinal);
        setRgm(rgm);
        this.id = contadorId++;
    }

    public void setTipoIngresso(TipoIngresso tipoIngresso) {
        if (tipoIngresso == null) {
            throw new IllegalArgumentException("Tipo do ingresso deve ser válido.");
        }
        this.tipoIngresso = tipoIngresso;
    }
    public void setVisitante(Visitante visitante) {
        if (visitante == null) {
            throw new IllegalArgumentException("Visitante não pode ser nulo.");
        }
        this.visitante = visitante;
    }
    public void setApresentacao(Apresentacao apresentacao) {
        if (apresentacao == null) {
            throw new IllegalArgumentException("Apresentação não pode ser nula.");
        }
        this.apresentacao = apresentacao;
    }
    public void setValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do ingresso deve ser maior que zero.");
        }
        this.valor = valor;
    }
    public void setValorFinal(double valorFinal) {
        if (valorFinal <= 0) {
            throw new IllegalArgumentException("Valor final do ingresso deve ser maior que zero.");
        }
        this.valorFinal = valorFinal;
    }

    public void setRgm(String rgm) {
        if (rgm.isEmpty() || rgm == null) {

        }
        this.rgm = rgm;
    }

    public int getId() {
        return id;
    }
    public TipoIngresso getTipoIngresso() {
        return tipoIngresso;
    }
    public Visitante getVisitante() {
        return visitante;
    }
    public Apresentacao getApresentacao() {
        return apresentacao;
    }
    public double getValor() {
        return valor;
    }
    public double getValorFinal() {
        return valorFinal;
    }
    public String getRgm() {
        return rgm;
    }

    @Override
    public String toString() {
        return "Ingresso " +getTipoIngresso()+
                "\n ID: " +getId()+
                "\n Visitante: "+getVisitante().getNome()+ " ("+getVisitante().getTipoDocumento()+": " +getVisitante().getDocumento()+")"+
                "\n Apresentação: "+getApresentacao().getNomeApresentacao()+ " ("+getApresentacao().getTipo()+")"+
                "\n Valor base do ingresso: "+getValor()+
                "\n Valor final: "+getValorFinal()+
                "\n RGM: "+getRgm();
    }

    public void exibirDetalhes() {
        System.out.println("\n--------------------------------");
        System.out.println("INGRESSO ("+getTipoIngresso()+")");
        System.out.print(" ID: " + getId());
        System.out.println("Visitante: " +getVisitante().getNome()+ " ("+getVisitante().getTipoDocumento()+": " +getVisitante().getDocumento()+")");
        System.out.println("Apresentação: "+getApresentacao().getNomeApresentacao()+ " ("+getApresentacao().getTipo()+")");
        System.out.println("Valor base do ingresso: " + getValor());
        System.out.println("Valor final: " + getValorFinal());
        if (tipoIngresso.equals(TipoIngresso.MEIA)) {
            System.out.println("RGM: " +getRgm());
        }
    }
    public static void atualizarContador(int id) {
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }
}
