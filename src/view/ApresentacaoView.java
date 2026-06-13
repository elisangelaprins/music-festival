package view;

import model.Artista;
import model.abstratas.Apresentacao;
import model.enums.TipoArtista;
import model.enums.TipoShow;

import java.util.List;
import java.util.Scanner;

public class ApresentacaoView {
    private final Scanner scanner;

    public ApresentacaoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void limparBuffer() {
        scanner.nextLine();
    }

    private int lerOpcao() {
        System.out.println("Escolha: ");
        return scanner.nextInt();
    }

    public int lerId() {
        System.out.print("Informe o ID: ");
        int id = scanner.nextInt();
        limparBuffer();
        return id;
    }

    public String lerData() {
        System.out.print("Digite a data para a apresentação (Ex.: DD/MM/AAAA): ");
        return scanner.nextLine();
    }

    public String lerHora() {
        System.out.print("Informe a hora da apresentação 0(Ex.: HH:MM):");
        return scanner.nextLine();
    }


    public String lerNomeApresentacao() {
        System.out.print("Digite o nome da Apresentação:");
        return scanner.nextLine();
    }

    public String lerNomeEntrevista() {
        System.out.print("Digite o nome da Entrevista (Ex.: Entrevista com Ivete Sangalo): ");
        return scanner.nextLine();
    }

    public String lerNomeArtista() {
        System.out.print("Digite o nome Artístico: ");
        return scanner.nextLine();
    }

    public String lerNomeEntrevistador() {
        System.out.print("Digite o nome do Entrevistador(a): ");
        return scanner.nextLine();
    }

    public String lerTemaEntrevista() {
        System.out.print("Digite o tema da Entrevista (EX.: Infância, Novidades na carreira...): ");
        return scanner.nextLine();
    }

    public int lerDuracao() {
        System.out.print("Digite a duração da apresentação (EX.: 60):");
        int duracao = scanner.nextInt();
        limparBuffer();
        return duracao;
    }

    public TipoShow lerTipoShow() {
        System.out.println("Tipo de Show:");
        System.out.println("1 - Acústico");
        System.out.println("2 - Completo");
        System.out.println("3 - Ao Vivo");
        System.out.println("4 - PlayBack");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        limparBuffer();

        switch (opcao) {
            case 1:
                return TipoShow.ACUSTICO;

            case 2:
                return TipoShow.COMPLETO;

            case 3:
                return TipoShow.AO_VIVO;

            case 4:
                return TipoShow.PLAYBACK;

            default:
                throw new IllegalArgumentException("Opção inválida");
        }
    }

    public int lerTipoListagem() {
        System.out.println("=== LISTAR APRESENTAÇÕES ===");
        System.out.println("1 - Todas as apresentações");
        System.out.println("2 - Apenas Shows");
        System.out.println("3 - Apenas Entrevistas");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        limparBuffer();
        return opcao;
    }

    public double lerCacheShow() {
        System.out.print("Digite o valor do cachê do Show (Ex.: 5000,00): ");
        double cache = scanner.nextDouble();
        limparBuffer();
        return cache;
    }

    public void exibirApresentacoes(List<Apresentacao> apresentacoes) {
        if (apresentacoes.isEmpty()) {
            System.out.println("Nenhuma apresentação Cadastrada.");
            return;
        }
        for (Apresentacao apresentacao : apresentacoes) {
            apresentacao.exibirDetalhes();
        }
    }
}
