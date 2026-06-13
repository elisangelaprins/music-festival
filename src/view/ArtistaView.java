package view;

import model.Artista;
import model.enums.TipoArtista;
import model.enums.TipoDocumento;
import java.util.List;
import java.util.Scanner;

public class ArtistaView {
    private final Scanner scanner;

    public ArtistaView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void limparBuffer() {
        scanner.nextLine();
    }

    public String lerNome() {
        System.out.print("Informe o nome do Artista: ");
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

    public TipoArtista lerTipoArtista(){
        System.out.println("Tipo de Artista:");
        System.out.println("1 - Solo");
        System.out.println("2 - Dupla");
        System.out.println("3 - Banda");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        limparBuffer();

        switch (opcao) {
            case 1:
                return TipoArtista.SOLO;

            case 2:
                return TipoArtista.DUPLA;

            case 3:
                return TipoArtista.BANDA;

            default:
                throw new IllegalArgumentException("Opção inválida");
        }
    }

    public String lerNomeArtistico(TipoArtista tipoArtista)
    {
        if (tipoArtista == TipoArtista.SOLO) {
            System.out.print("Informe o nome artístico: ");
        } else if (tipoArtista == TipoArtista.DUPLA) {
            System.out.print("Informe o nome da Dupla:");
        } else {
            System.out.print("Informe o nome da Banda: ");
        }
        return scanner.nextLine();
    }

    public String lerNomeBusca() {
        System.out.print("Informe o nome ou nome artístico: ");
        return scanner.nextLine();
    }

    public String lerGeneroMusical() {
        System.out.print("Informe o gênero musical: ");
        return scanner.nextLine();
    }

    public String lerContatoTelefonico() {
        System.out.print("Informe um telefone para contato: ");
        return scanner.nextLine();
    }

    public int lerId() {
        System.out.print("Informe o id: ");
        int id = scanner.nextInt();
        limparBuffer();
        return id;
    }

    public void exibirArtistas(List<Artista> artistas) {
        if (artistas.isEmpty()) {
            System.out.println("Nenhum artista Cadastrado.");
            return;
        }
        for (Artista artista : artistas) {
            artista.exibirDetalhes();
        }
    }
}
