package view;

import controller.ArtistaController;
import util.LogUtil;
import util.TipoLog;

import java.util.Scanner;

public class MenuPrincipalView {
    private final Scanner scanner;
    private final ArtistaController artistaController;

    public MenuPrincipalView() {
        scanner = new Scanner(System.in);
        ArtistaView artistaView = new ArtistaView();
        this.artistaController = new ArtistaController(artistaView);
    }


    public void iniciar(){
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
                case 3:
                    menuPalco();
                    break;
                case 4:
                    menuAgenda();
                    break;
                case 5:
                    menuVisitante();
                    break;
                case 6:
                    menuIngresso();
                    break;
                case 7:
                    menuStaff();
                    break;
                case 8:
                    menuCredencial();
                    break;
                case 9:
                    menuPatrocinador();
                    break;
                case 10:
                    menuRelatorio();
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
        System.out.println("10 - Relatório");
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

    private int lerOpcao() {
        System.out.println("Escolha: ");
        return scanner.nextInt();
    }

    private void limparBuffer() {
        scanner.nextLine();
    }

    private void fecharScanner(){
        scanner.close();
    }
}
