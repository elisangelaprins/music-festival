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
                    artistaController.cadastrarArtista();
                    break;

                case 2:
                    artistaController.listarArtistas();
                    break;

                case 3:
                    artistaController.alterarArtista();
                    break;

                case 4:
                    artistaController.removerArtista();
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
        System.out.println("--- MENU ---");
        System.out.println("1 - Cadastrar Artista");
        System.out.println("2 - Listar Artista");
        System.out.println("3 - Alterar Artista");
        System.out.println("4 - Remover Artista");
        System.out.println("0 - Sair");
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
