package view;

import model.Ingresso;
import model.enums.TipoIngresso;

import java.util.List;
import java.util.Scanner;

public class IngressoView {
    private final Scanner scanner;

    public IngressoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMsg(String msg) {
        System.out.println(msg);
    }
    public void limparBuffer() {
        scanner.nextLine();
    }

    public TipoIngresso lerTipoIngresso() {
        System.out.println("Tipo de ingresso:");
        System.out.println("1 - Inteira");
        System.out.println("2 - Meia");
        System.out.println("3 - VIP");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        limparBuffer();
        switch (opcao) {
            case 1:
                return TipoIngresso.INTEIRA;
            case 2:
                return TipoIngresso.MEIA;
            case 3:
                return TipoIngresso.VIP;
            default:
                throw new IllegalArgumentException("Opção inválida.");
        }
    }

    public int lerIdIngresso() {
        System.out.print("Informe o id do ingresso: ");
        return scanner.nextInt();
    }
    public int lerIdVisitante() {
        System.out.print("Informe o id do visitante: ");
        return scanner.nextInt();
    }
    public int lerIdApresentacao() {
        System.out.print("Informe o id da apresentação: ");
        return scanner.nextInt();
    }
    public double lerValor() {
        System.out.print("Informe o valor do ingresso: ");
        return scanner.nextInt();
    }
    public String lerRGM() {
        System.out.print("Informe o RGM do visitante: ");
        return scanner.nextLine();
    }
    public double lerTaxaVip() {
        System.out.print("Informe a taxa do ingresso VIP: ");
        return scanner.nextDouble();
    }

    public void exibirIngressos(List<Ingresso> ingressos) {
        if (ingressos.isEmpty()) {
            System.out.println("Nenhum ingresso encontrado.");
            return;
        }
        for (Ingresso i : ingressos) {
            i.exibirDetalhes();
        }
    }
}
