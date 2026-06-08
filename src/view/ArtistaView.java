package view;

import model.Artista;
import model.enums.TipoArtista;
import model.enums.TipoDocumento;
import java.util.List;
import java.util.Scanner;

public class ArtistaView {
    private final Scanner scanner;

    public ArtistaView() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void limparBuffer() {
        scanner.nextLine();
    }

    public String lerNome() {
        System.out.println("Informe o nome do Artista: ");
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
        String documento = scanner.nextLine();

        tipoDocumento.validar(documento);
        return documento;

    }

    public TipoArtista lerTipoArtista(){
        System.out.println("Tipo de Artista:");
        System.out.println("1 - Solo");
        System.out.println("2 - Dupla");
        System.out.println("3 - Banda");
        System.out.println("Escolha: ");

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
            System.out.println("Informe o nome artístico: ");
        } else if (tipoArtista == TipoArtista.DUPLA) {
            System.out.println("Informe o nome da Dupla:");
        } else {
            System.out.println("Informe o nome da Banda: ");
        }
        return scanner.nextLine();
    }

    public String lerGeneroMusical() {
        System.out.println("Informe o gênero musical: ");
        return scanner.nextLine();
    }

    public String lerContatoTelefonico() {
        System.out.println("Informe um telefone para contato: ");
        return scanner.nextLine();
    }

    public int lerId() {
        System.out.println("Informe o id: ");
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
