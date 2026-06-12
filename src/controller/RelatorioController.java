package controller;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import model.Relatorio;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;
import view.RelatorioView;

public class RelatorioController {
    private static final String ARQUIVO = "relatorios.dat";
    private final RelatorioView view;
    private HashMap<Integer, Relatorio> relatorios;
    private int proximoId = 1;

    public RelatorioController(RelatorioView view) {
        this.view = view;
        this.relatorios = new HashMap<>();

        try {
            Map<Integer, ?> carregado = ArquivoUtil.carregarArquivo(ARQUIVO);

            if (carregado != null) {
                this.relatorios = (HashMap<Integer, Relatorio>) carregado;

                for (Integer id : relatorios.keySet()) {
                    if (id >= proximoId) {
                        proximoId = id +1;
                    }
                }
            }

            LogUtil.log(TipoLog.INFO, "Arquivo de relatórios carregados com sucesso.");
        } catch (Exception e) {
            LogUtil.log(TipoLog.AVISO, "Nenhum arquivo de dados prévios encontrado para relatórios.");
        }
    }
    public void processarExibicaoEExportacao(String titulo, String conteudo) {
        try {
            Relatorio novoRelatorio= new Relatorio(titulo, conteudo);
            view.exibirRelatorio(novoRelatorio.getTitulo(),novoRelatorio.gerarRelatorioGeral());

            String resposta = view.lerConfirmacaoSalvar();

            if (resposta.equals("S")) {

             relatorios.put(proximoId, novoRelatorio);
             proximoId++;

                ArquivoUtil.salvarArquivo(relatorios, ARQUIVO);

                LogUtil.log(TipoLog.INFO, "Relatório cadastrado/salvo:   " + novoRelatorio.getTitulo());
                view.mostrarMensagem("Relatório exportado com sucesso pelo ArquivoUtil!");

            } else {
                view.mostrarMensagem("Exportação cancelada.");
            }
        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Erro ao processar relatório: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void gerarRelatorioDoMenu(String titulo, List<?> lista) {
        String texto = "";

        if (lista == null || lista.isEmpty()) {
            texto = "Nenhum registro encontrado para esse relatório. \n";
        } else {
            for (Object objeto : lista) {
                texto += objeto.toString() + "\n-------------------\n";
            }
        }

        processarExibicaoEExportacao(titulo, texto);
    }
}
