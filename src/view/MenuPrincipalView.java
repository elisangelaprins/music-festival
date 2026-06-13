package view;

import controller.ApresentacaoController;
import controller.PatrocinadorController;
import controller.ArtistaController;
import controller.PropostaController;
import util.LogUtil;
import util.TipoLog;

import java.util.Scanner;

public class MenuPrincipalView {
    private final Scanner scanner;
    private final ArtistaController artistaController;
    private final ApresentacaoController apresentacaoController;
    private final PatrocinadorController patrocinadorController;
    private final PropostaController propostaController;

    public MenuPrincipalView() {
        scanner = new Scanner(System.in);
        ArtistaView artistaView = new ArtistaView(scanner);
        this.artistaController = new ArtistaController(artistaView);
        ApresentacaoView apresentacaoView = new ApresentacaoView(scanner);
        this.apresentacaoController = new ApresentacaoController(apresentacaoView, artistaController);
        PatrocinadorView patrocinadorView = new PatrocinadorView(scanner);
        this.patrocinadorController = new PatrocinadorController(patrocinadorView);
        PropostaView propostaView = new PropostaView(scanner);
        this.propostaController = new PropostaController(propostaView, patrocinadorController);

    }


    public void iniciar() {
        LogUtil.log(TipoLog.INFO, "Sistema iniciado pelo usuário");

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            limparBuffer();

            switch (opcao) {
                case 1:
                    menuArtista();
                    break;
                case 2:
                    menuApresentacao();
                    break;
//                case 3:
//                    menuPalco();
//                    break;
//                case 4:
//                    menuAgenda();
//                    break;
//                case 5:
//                    menuVisitante();
//                    break;
//                case 6:
//                    menuIngresso();
//                    break;
//                case 7:
//                    menuStaff();
//                    break;
//                case 8:
//                    menuCredencial();
//                    break;
                case 9:
                    menuPatrocinador();
                    break;
                case 10:
                    menuProposta();
                    break;
                case 0:
                    LogUtil.log(TipoLog.INFO, "Sistema encerrado pelo usuário.");
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        fecharScanner();
    }

    private void exibirMenu() {
        System.out.println("=== FESTIVAL DE MÚSICA ===");
        System.out.println("1 - Artistas");
        System.out.println("2 - Apresentações");
        System.out.println("3 - Palcos");
        System.out.println("4 - Agenda");
        System.out.println("5 - Visitante");
        System.out.println("6 - Ingresso");
        System.out.println("7 - Staff");
        System.out.println("8 - Credencial");
        System.out.println("9 - Patrocinador");
        System.out.println("10 - Propostas");
        System.out.println("0 - Sair");
    }

    private void menuArtista() {
        int opcao;
        do {
            System.out.println("=== ARTISTAS ===");
            System.out.println("1 - Cadastrar artista");
            System.out.println("2 - Listar artistas");
            System.out.println("3 - Buscar artista pelo Nome");
            System.out.println("4 - Alterar artista");
            System.out.println("5 - Remover artista");
            System.out.println("0 - Voltar ao menu inicial");
            opcao = lerOpcao();
            limparBuffer();
            switch (opcao) {
                case 1:
                    artistaController.cadastrarArtista();
                    break;
                case 2:
                    artistaController.listarArtistas();
                    break;
                case 3:
                    artistaController.buscarPorNome();
                    break;
                case 4:
                    artistaController.alterarArtista();
                    break;
                case 5:
                    artistaController.removerArtista();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }

    private void menuApresentacao() {
        int opcao;
        do {
            System.out.println("=== APRESENTAÇÕES ===");
            System.out.println("1 - Cadastrar Show");
            System.out.println("2 - Cadastrar Entrevista");
            System.out.println("3 - Listar Apresentações");
            System.out.println("4 - Buscar apresentação por ID");
            System.out.println("5 - Buscar apresentação pelo Artista");
            System.out.println("6 - Alterar apresentação");
            System.out.println("7 - Remover apresentação");
            System.out.println("8 - Exibir Total de Cachês dos Shows");
            System.out.println("0 - Voltar ao menu inicial");
            opcao = lerOpcao();
            limparBuffer();
            switch (opcao) {
                case 1:
                    apresentacaoController.cadastrarShow();
                    break;
                case 2:
                    apresentacaoController.cadastrarEntrevista();
                    break;
                case 3:
                    apresentacaoController.listarPorTipo();
                    break;
                case 4:
                    apresentacaoController.exibirPorId();
                    break;
                case 5:
                    apresentacaoController.buscarPorArtista();
                    break;
                case 6:
                    apresentacaoController.alterarApresentacao();
                    break;
                case 7:
                    apresentacaoController.removerApresentacao();
                    break;
                case 8:
                    apresentacaoController.mostrarTotalCaches();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }

    private void menuPatrocinador() {
        int opcao;

        do {
            System.out.println("=== PATROCINADORES ===");
            System.out.println("1 - Cadastrar patrocinador");
            System.out.println("2 - Listar patrocinadores");
            System.out.println("3 - Alterar patrocinador");
            System.out.println("4 - Remover patrocinador");
            System.out.println("5 - Exibir Total Patrocinado");
            System.out.println("0 - Voltar ao menu inicial");

            opcao = lerOpcao();
            limparBuffer();

            switch (opcao) {
                case 1:
                    patrocinadorController.cadastrarPatrocinador();
                    break;

                case 2:
                    patrocinadorController.listarPatrocinadores();
                    break;

                case 3:
                    patrocinadorController.alterarPatrocinador();
                    break;

                case 4:
                    patrocinadorController.removerPatrocinador();
                    break;

                case 5:
                    patrocinadorController.mostrarTotalPatrocinado();
                    break;

                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }
    private void menuProposta() {
        int opcao;
        do {
            System.out.println("\n=== PROPOSTAS DE PATROCÍNIO ===");
            System.out.println("1 - Cadastrar proposta");
            System.out.println("2 - Listar propostas");
            System.out.println("3 - Alterar proposta");
            System.out.println("4 - Remover proposta");
            System.out.println("0 - Voltar ao menu inicial");

            opcao = lerOpcao();
            limparBuffer();

            switch (opcao) {
                case 1:
                    propostaController.cadastrarProposta();
                    break;
                case 2:
                    propostaController.listarPropostas();
                    break;
                case 3:
                    propostaController.alterarProposta();
                    break;
                case 4:
                    propostaController.removerProposta();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }


    private int lerOpcao() {
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    private void limparBuffer() {
        scanner.nextLine();
    }

    private void fecharScanner() {
        scanner.close();
    }
}
