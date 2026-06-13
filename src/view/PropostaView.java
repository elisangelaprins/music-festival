package view;
import model.Proposta;
import model.enums.StatusProposta;
import java.util.List;
import java.util.Scanner;
public class PropostaView {
    private final Scanner scanner;

    public PropostaView(Scanner scanner) {
        this.scanner = scanner;
    }
    public String lerTitulo() {
        System.out.print("Informe o título da Proposta: ");
        return scanner.nextLine();
    }

    public String lerDescricao() {
        System.out.print("Informe a descrição da Proposta: ");
        return scanner.nextLine();
    }

    public double lerValorProposto() {
        System.out.print("Informe o valor proposto: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
    public StatusProposta lerStatus() {
        System.out.println("Selecione o Status da Proposta:");
        System.out.println("1 - PENDENTE");
        System.out.println("2 - APROVADA");
        System.out.println("3 - RECUSADA");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                return StatusProposta.PENDENTE;
            case 2:
                return StatusProposta.APROVADA;
            case 3:
                return StatusProposta.RECUSADA;
            default:
                return StatusProposta.PENDENTE;
        }

    }

    public int lerId() {
        System.out.print("Informe o ID da Proposta: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public int lerIdPatrocinador() {
        System.out.print("Informe o ID do Patrocinador associado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public void exibirProposta(List<Proposta> propostas) {
        if (propostas.isEmpty()) {
            System.out.println("Nenhuma proposta cadastrada no momento.");
            return;
        }
        System.out.println("\n=== LISTA DE PROPOSTAS ===");
        for (Proposta p : propostas) {
            System.out.println(p);
            System.out.println("  Descrição:  " + p.getDescricao());
            System.out.println("------------------------------------------");
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
