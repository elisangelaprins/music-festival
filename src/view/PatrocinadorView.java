package view;
import model.Patrocinador;
import model.enums.CategoriaPatrocinio;
import java.util.List;
import java.util.Scanner;
public class PatrocinadorView {
    private final Scanner scanner;


    public PatrocinadorView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void limparBuffer() {
        scanner.nextLine();
    }

    public String lerNome() {
        System.out.println("Informe o nome do Patrocinador: ");
        return scanner.nextLine();
    }

    public String lerCnpj() {
        System.out.println("Informe o CNPJ do Patrocinador: ");
        return scanner.nextLine();
    }

    public double lerValorPatrocinio() {
        System.out.println("Informe o valor do Patrocínio: ");
        double valor = scanner.nextDouble();
        limparBuffer();
        return valor;
    }

    public CategoriaPatrocinio lerCategoriaPatrocinio() {
        System.out.println("Categoria do Patrocínio: ");
        System.out.println("1 - OURO");
        System.out.println("2 - PRATA");
        System.out.println("3 - BRONZE");
        System.out.println("Escolha: ");

        int opcao = scanner.nextInt();
        limparBuffer();

        switch (opcao) {
            case 1:
                return CategoriaPatrocinio.OURO;

            case 2:
                return CategoriaPatrocinio.PRATA;

            case 3:
                return CategoriaPatrocinio.BRONZE;

            default:
                throw new IllegalArgumentException("Opção inválida.");
        }
    }

    public int lerId() {
        System.out.println("Informe o ID do Patrocinador: ");
        int id = scanner.nextInt();
        limparBuffer();
        return id;
    }

    public void exibirPatrocinadores(List<Patrocinador> patrocinadores) {
        if (patrocinadores.isEmpty()) {
            System.out.println("Nenhum patrocinador cadastrado.");
            return;
        }

        for (Patrocinador p : patrocinadores) {
            System.out.println("------------");
            System.out.println(p);
        }
    }
}
