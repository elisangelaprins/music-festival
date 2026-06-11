package view;
import java.util.Scanner;
public class RelatorioView {
    private final Scanner scanner;

    public RelatorioView() {
        this.scanner = new Scanner(System.in);

    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirRelatorio(String titulo, String conteudo) {
        System.out.println("\n=============================");
        System.out.println(" " + titulo.toUpperCase());
        System.out.println("===============================");
        System.out.println(conteudo);
        System.out.println("================================");
    }

    public String lerConfirmacaoSalvar() {
        System.out.println("Deseja exportar este relatório para arquivo. txt ? (S/N): ");
        return scanner.nextLine().trim().toUpperCase();
    }

}
