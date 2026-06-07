package view;
import controller.PatrocinadorController;
import model.Patrocinador;
import model.enums.CategoriaPatrocinio;
import java.util.Map;
import java.util.Scanner;
public class PatrocinadorView {

    private PatrocinadorController controller;
    private Scanner scanner;

    public PatrocinadorView() {
        controller = new PatrocinadorController();
        scanner = new Scanner(System.in);
    }

    public void menu() {

        int opcao;

        do {
            System.out.println("\n=================================");
            System.out.println("     FESTIVAL DE MÚSICA");
            System.out.println("     MENU PATROCINADOR");
            System.out.println("===================================");
            System.out.println("1 - Cadastrar Patrocinador");
            System.out.println("2 - Listar Patrocinadores");
            System.out.println("3 - Buscar Patrocinador");
            System.out.println("4 - Atualizar Patrocinador");
            System.out.println("5 - Remover Patrocinador");
            System.out.println("6 - Exibir Total Patrocinado");
            System.out.println("0 - Voltar");
            System.out.println("==================================");
            System.out.println("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {

                case 1:
                    cadastrarPatrocinador();
                    break;

                case 2:
                    listarPatrocinadores();
                    break;

                case 3:
                    buscarPatrocinador();
                    break;

                case 4:
                    atualizarPatrocinador();
                    break;

                case 5:
                    removerPatrocinador();
                    break;

                case 6:
                    mostrarTotalPatrocinado();
                    break;

                case 0:
                    System.out.println("Retornando ao menu principal....");
                    break;

                default:
                    System.out.println("Opção inválida.");

            }

        } while (opcao != 0);
    }

    private void cadastrarPatrocinador() {

        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.println("CNPJ: ");
            String cnpj = scanner.nextLine();

            System.out.println("Valor do patrocínio: ");
            double valor = Double.parseDouble(scanner.nextLine());

            System.out.println("Categoria:");
            System.out.println("1 - OURO");
            System.out.println("2 - PRATA");
            System.out.println("3 - BRONZE");

            int opcaoCategoria = Integer.parseInt(scanner.nextLine());

            CategoriaPatrocinio categoria;

            switch (opcaoCategoria) {
                case 1:
                    categoria = CategoriaPatrocinio.OURO;
                    break;

                case 2:
                    categoria = CategoriaPatrocinio.PRATA;
                    break;
                case 3:
                    categoria = CategoriaPatrocinio.BRONZE;
                    break;
                default:
                    throw new IllegalArgumentException("Categoria inválida");

            }

            Patrocinador patrocinador =
                 new Patrocinador(nome,cnpj,valor, categoria);

            controller.adicionarPatrocinador(patrocinador);

            System.out.println("Patrocinador cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPatrocinadores() {
        Map<Integer, Patrocinador> lista = controller.listarPatrocinadores();

        if (lista.isEmpty()) {
            System.out.println("Nenhum patrocinador cadastrado.");
            return;
        }

        for (Patrocinador p : lista.values()) {
            System.out.println("------------");
            System.out.println(p);
        }
    }

    private void buscarPatrocinador() {

        System.out.println("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Patrocinador p = controller.buscarPatrocinador(id);

        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Patrocinador não encontrado.");
        }
    }

    private void atualizarPatrocinador() {
        try {

            System.out.println("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("Nome: ");
            String nome = scanner.nextLine();

            System.out.println("CNPJ: ");
            String cnpj = scanner.nextLine();

            System.out.println("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine());

            System.out.println("Categoria:");
            System.out.println("1 - OURO");
            System.out.println("2 - PRATA");
            System.out.println("3 - BRONZE");

            int opcaoCategoria = Integer.parseInt(scanner.nextLine());

            CategoriaPatrocinio categoria;

            switch (opcaoCategoria) {
                case 1:
                    categoria = CategoriaPatrocinio.OURO;
                    break;
                case 2:
                    categoria = CategoriaPatrocinio.PRATA;
                    break;
                case 3:
                    categoria = CategoriaPatrocinio.BRONZE;
                    break;
                default:
                    throw new IllegalArgumentException("Categoria inválida");
            }

            boolean ok = controller.atualizarPatrocinador(
                    id, nome, cnpj, valor, categoria
            );

            if (ok) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Patrocinador não encontrado.");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    private void removerPatrocinador() {

        System.out.println("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean ok = controller.removerPatrocinador(id);

        if (ok) {
            System.out.println("Removido com sucesso!");
        } else {
            System.out.println("Patrocinador não encontrado.");
        }
    }

    private void mostrarTotalPatrocinado() {
        System.out.println("Total patrocinado: R$ " + controller.calcularTotalPatrocinio());
    }


}
