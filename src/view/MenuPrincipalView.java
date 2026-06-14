package view;

import controller.*;
import util.LogUtil;
import util.TipoLog;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipalView {
    private final Scanner scanner;
    private final ArtistaController artistaController;
    private final VisitanteController visitanteController;
    private final IngressoController ingressoController;
    private final ApresentacaoController apresentacaoController;
    private final PatrocinadorController patrocinadorController;
    private final PropostaController propostaController;
    private final StaffController staffController;
    private final CredencialController credencialController;

    public MenuPrincipalView() {
        scanner = new Scanner(System.in);
        ArtistaView artistaView = new ArtistaView(scanner);
        this.artistaController = new ArtistaController(artistaView);
        VisitanteView visitanteView = new VisitanteView(scanner);
        this.visitanteController = new VisitanteController(visitanteView);
        IngressoView ingressoView = new IngressoView(scanner);
        this.ingressoController = new IngressoController(ingressoView);
        ApresentacaoView apresentacaoView = new ApresentacaoView(scanner);
        this.apresentacaoController = new ApresentacaoController(apresentacaoView, artistaController);
        PatrocinadorView patrocinadorView = new PatrocinadorView(scanner);
        this.patrocinadorController = new PatrocinadorController(patrocinadorView);
        PropostaView propostaView = new PropostaView(scanner);
        this.propostaController = new PropostaController(propostaView, patrocinadorController);
        StaffView staffView = new StaffView(scanner);
        this.staffController = new StaffController(staffView);
        CredencialView credencialView = new CredencialView(scanner);
        this.credencialController = new CredencialController(credencialView, staffController, artistaController);
    }


    public void iniciar() {
        LogUtil.log(TipoLog.INFO, "Sistema iniciado pelo usuário");

        int opcao = 90;
        do {

            try {
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
                    case 3:
                        menuVisitante();
                        break;
                    case 4:
                        menuIngresso();
                        break;
                    case 5:
                        menuStaff();
                        break;
                    case 6:
                        menuCredencial();
                        break;
                    case 7:
                        menuPatrocinador();
                        break;
                    case 8:
                        menuProposta();
                        break;
                    case 0:
                        LogUtil.log(TipoLog.INFO, "Sistema encerrado pelo usuário.");
                        System.out.println("Encerrando...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Insira um valor válido.");
                limparBuffer();
            }
        } while (opcao != 0);
        fecharScanner();
    }

    private void exibirMenu() {
        System.out.println("=== FESTIVAL DE MÚSICA ===");
        System.out.println("1 - Artistas");
        System.out.println("2 - Apresentações");
        System.out.println("3 - Visitante");
        System.out.println("4 - Ingresso");
        System.out.println("5 - Staff");
        System.out.println("6 - Credencial");
        System.out.println("7 - Patrocinador");
        System.out.println("8 - Propostas");
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

    private void menuVisitante() {
        int opcao = 90;
        do {
            try {
                System.out.println("=== VISITANTES ===");
                System.out.println("1 - Cadastrar visitante");
                System.out.println("2 - Listar visitantes");
                System.out.println("3 - Buscar visitante pelo documento");
                System.out.println("4 - Alterar visitante");
                System.out.println("5 - Remover visitante");
                System.out.println("0 - Voltar ao menu inicial");
                opcao = lerOpcao();
                limparBuffer();

                switch (opcao) {
                    case 1:
                        visitanteController.cadastrarVisitante();
                        break;
                    case 2:
                        visitanteController.listarVisitantes();
                        break;
                    case 3:
                        visitanteController.buscarPorDocumento();
                        break;
                    case 4:
                        visitanteController.alterarVisitante();
                        break;
                    case 5:
                        visitanteController.removerVisitante();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Insira um valor válido.");
                limparBuffer();
            }
        } while (opcao != 0);
    }

    private void menuIngresso() {
        int opcao = 90;
        do {
            try {
                System.out.println("=== INGRESSO ===");
                System.out.println("1 - Cadastrar ingresso");
                System.out.println("2 - Listar ingressos");
                System.out.println("3 - Buscar ingresso pela apresentação");
                System.out.println("4 - Buscar ingresso pelo visitante");
                System.out.println("5 - Alterar ingresso");
                System.out.println("6 - Remover ingresso");
                System.out.println("0 - Voltar ao menu inicial");
                opcao = lerOpcao();
                limparBuffer();

                switch (opcao) {
                    case 1:
                        ingressoController.cadastrarIngresso(visitanteController.getVisitantes(), apresentacaoController.getApresentacoes());
                        break;
                    case 2:
                        ingressoController.listarIngressos();
                        break;
                    case 3:
                        ingressoController.buscarPorApresentacao();
                        break;
                    case 4:
                        ingressoController.buscarPorVisitante();
                        break;
                    case 5:
                        ingressoController.alterarIngresso(visitanteController.getVisitantes());
                        break;
                    case 6:
                        ingressoController.removerIngresso();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Insira um valor válido.");
                limparBuffer();
            }
        } while (opcao != 0);
    }

    private void menuStaff() {
        int opcao;
        do {
            System.out.println("=== STAFF ===");
            System.out.println("1 - Cadastrar staff");
            System.out.println("2 - Listar staffs");
            System.out.println("3 - Alterar staff");
            System.out.println("4 - Remover staff");
            System.out.println("0 - Voltar ao menu inicial");
            opcao = lerOpcao();
            limparBuffer();
            switch (opcao) {
                case 1:
                    staffController.cadastrarStaff();
                    break;
                case 2:
                    staffController.listarStaffs();
                    break;
                case 3:
                    staffController.alterarStaff();
                    break;
                case 4:
                    staffController.removerStaff();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }

    private void menuCredencial() {
        int opcao;
        do {
            System.out.println("=== CREDENCIAIS ===");
            System.out.println("1 - Cadastrar credencial");
            System.out.println("2 - Listar credenciais");
            System.out.println("3 - Alterar credencial");
            System.out.println("4 - Remover credencial");
            System.out.println("0 - Voltar ao menu inicial");
            opcao = lerOpcao();
            limparBuffer();
            switch (opcao) {
                case 1:
                    credencialController.cadastrarCredencial();
                    break;
                case 2:
                    credencialController.listarCredenciais();
                    break;
                case 3:
                    credencialController.alterarCredencial();
                    break;
                case 4:
                    credencialController.removerCredencial();
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
        int opcao = 90;
        do {
            try {

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
            } catch (InputMismatchException e) {
                System.out.println("O valor inserido não é válido.");
                limparBuffer();
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
