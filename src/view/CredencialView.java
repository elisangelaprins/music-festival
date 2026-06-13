package view;

import model.Credencial;
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

    public String lerTipoAcesso() {
        System.out.println("Informe o tipo de acesso (ex: Total, Backstage, Geral): ");
        return scanner.nextLine();
    }

    public int lerStaffId() {
        System.out.println("Informe o id do Staff dono da credencial: ");
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
