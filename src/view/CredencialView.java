package view;

import model.Credencial;
import model.enums.TipoAcesso;
import java.util.List;
import java.util.Scanner;

public class CredencialView {
    private final Scanner scanner;

    public CredencialView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void limparBuffer() {
        scanner.nextLine();
    }

    public TipoAcesso lerTipoAcesso() {
        System.out.println("Informe o tipo de acesso:");
        System.out.println("1 - Total");
        System.out.println("2 - Backstage");
        System.out.println("3 - Geral");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        limparBuffer();
        switch (opcao) {
            case 1:
                return TipoAcesso.TOTAL;
            case 2:
                return TipoAcesso.BACKSTAGE;
            case 3:
                return TipoAcesso.GERAL;
            default:
                throw new IllegalArgumentException("Opção inválida.");
        }
    }

    public int lerTipoTitular() {
        System.out.println("Informe o tipo de titular da credencial:");
        System.out.println("1 - Staff");
        System.out.println("2 - Artista");
        int tipo = scanner.nextInt();
        limparBuffer();
        return tipo;
    }

    public int lerStaffId() {
        System.out.println("Informe o id do Staff dono da credencial: ");
        int id = scanner.nextInt();
        limparBuffer();
        return id;
    }

    public int lerArtistaId() {
        System.out.println("Informe o id do Artista dono da credencial: ");
        int id = scanner.nextInt();
        limparBuffer();
        return id;
    }

    public int lerId() {
        System.out.println("Informe o id da credencial: ");
        int id = scanner.nextInt();
        limparBuffer();
        return id;
    }

    public void exibirCredenciais(List<Credencial> credenciais) {
        if (credenciais.isEmpty()) {
            System.out.println("Nenhuma credencial cadastrada.");
            return;
        }
        for (Credencial credencial : credenciais) {
            System.out.println("\n--------------------------------");
            System.out.println(credencial);
        }
    }
}
