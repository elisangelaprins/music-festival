package view;

import model.Staff;
import model.enums.TipoDocumento;
import java.util.List;
import java.util.Scanner;

public class StaffView {
    private final Scanner scanner;

    public StaffView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void limparBuffer() {
        scanner.nextLine();
    }

    public String lerNome() {
        System.out.println("Informe o nome do Staff: ");
        return scanner.nextLine();
    }

    public TipoDocumento lerTipoDocumento() {
        System.out.println("Tipo de Documento:");
        System.out.println("1 - CPF");
        System.out.println("2 - Passaporte");
        System.out.println("Escolha: ");

        int opcao = scanner.nextInt();
        limparBuffer();
        switch (opcao) {
            case 1:
                return TipoDocumento.CPF;
            case 2:
                return TipoDocumento.PASSAPORTE;
            default:
                throw new IllegalArgumentException("Opção inválida.");
        }
    }

    public String lerDocumento(TipoDocumento tipoDocumento) {
        System.out.print("Informe o número do documento: ");
        return scanner.nextLine();
    }

    public String lerCargo() {
        System.out.println("Informe o cargo (ex: Segurança, Técnico de Som, Produtor): ");
        return scanner.nextLine();
    }

    public String lerAreaAtuacao() {
        System.out.println("Informe a área de atuação (ex: Palco Principal, Backstage): ");
        return scanner.nextLine();
    }

    public int lerId() {
        System.out.println("Informe o id: ");
        int id = scanner.nextInt();
        limparBuffer();
        return id;
    }

    public void exibirStaffs(List<Staff> staffs) {
        if (staffs.isEmpty()) {
            System.out.println("Nenhum staff cadastrado.");
            return;
        }
        for (Staff staff : staffs) {
            staff.exibirDetalhes();
        }
    }
}
