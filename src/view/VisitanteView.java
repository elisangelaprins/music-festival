package view;

import model.Visitante;
import model.enums.TipoDocumento;

import java.util.List;
import java.util.Scanner;

public class VisitanteView {
    private final Scanner scanner;

    VisitanteView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMsg(String msg) {
        System.out.println(msg);
    }
    public void limparBuffer() {
        scanner.nextLine();
    }

    public int lerId() {
        System.out.print("Informe o id do visitante: ");
        return scanner.nextInt();
    }

    public String lerNome() {
        System.out.print("Informe o nome do visitante: ");
        return scanner.nextLine();
    }

    public TipoDocumento lerTipoDocumento() {
        System.out.println("Tipo de Documento:");
        System.out.println("1 - CPF");
        System.out.println("2 - Passaporte");
        System.out.print("Escolha: ");

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
        String documento = scanner.nextLine();

        tipoDocumento.validar(documento);
        return documento;
    }

    public String lerEmail() {
        System.out.print("Informe o Email do visitante: ");
        return scanner.nextLine();
    }
    public String lerTelefone() {
        System.out.print("Informe o telefone do visitante (DD+Número): ");
        return scanner.nextLine();
    }

    public void exibirVisitantes(List<Visitante> visitantes) {
        if  (visitantes.isEmpty()) {
            System.out.println("Nenhum visitante cadastrado.");
            return;
        }
        for (Visitante v : visitantes) {
            v.exibirDetalhes();
        }
    }
}
