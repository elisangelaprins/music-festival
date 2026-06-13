package controller;

import model.Proposta;
import model.Patrocinador;
import model.enums.StatusProposta;
import view.PropostaView;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropostaController {
    private static final String ARQUIVO = "propostas.dat";
    private final PropostaView view;
    private final PatrocinadorController patrocinadorController;
    private HashMap<Integer, Proposta> propostas;

    public PropostaController(PropostaView view, PatrocinadorController patrocinadorController) {
        this.view = view;
        this.patrocinadorController = patrocinadorController;
        this.propostas = new HashMap<>();

        try {
            Map<Integer, ?> carregado = ArquivoUtil.carregarArquivo(ARQUIVO);
            if (carregado != null) {
                this.propostas = (HashMap<Integer, Proposta>) carregado;

                int maiorId = 0;
                for (Integer id : propostas.keySet()) {
                    if (id > maiorId) {
                        maiorId = id;
                    }
                }
                Proposta.atualizarContador(maiorId);
            }
            LogUtil.log(TipoLog.INFO, "Arquivo binário de propostas carregado com sucesso.");
        } catch (Exception e) {
            LogUtil.log(TipoLog.AVISO, "Nenhum histórico de propostas encontrado. Criando base nova.");
        }
    }

    public void cadastrarProposta() {
        try {
            patrocinadorController.listarPatrocinadores();
            int idPatrocinador = view.lerIdPatrocinador();

            Patrocinador patrocinador = patrocinadorController.buscarPorId(idPatrocinador);

            if (patrocinador == null) {
                view.mostrarMensagem("Operação cancelada: Patrocinador não localizado! ");
                return;
            }

            String titulo = view.lerTitulo();
            String descricao = view.lerDescricao();
            double valor = view.lerValorProposto();
            StatusProposta status = view.lerStatus();

            Proposta novaProposta = new Proposta(titulo, descricao, valor, status, patrocinador);
            propostas.put(novaProposta.getId(), novaProposta);

            ArquivoUtil.salvarArquivo(propostas, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Proposta criada: " + novaProposta.getTitulo());
            view.mostrarMensagem("Proposta cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Erro de validação: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void listarPropostas() {
        view.exibirProposta(getPropostas());
    }

    public List<Proposta> getPropostas() {
        return new ArrayList<>(propostas.values());
    }

    public Proposta buscarPorId(int id) {
        Proposta proposta = propostas.get(id);

        if (proposta == null) {
            view.mostrarMensagem("Proposta com o ID " + id + " não foi encontrada.");
        }
        return proposta;
    }

    public void alterarProposta() {
        listarPropostas();
        int id = view.lerId();
        Proposta proposta = buscarPorId(id);

        if (proposta == null) return;

        try {
            String titulo = view.lerTitulo();
            proposta.setTitulo(titulo);

            String descricao = view.lerDescricao();
            proposta.setDescricao(descricao);

            double valor = view.lerValorProposto();
            proposta.setValorProposto(valor);

            StatusProposta status = view.lerStatus();
            proposta.setStatus(status);

            System.out.println("Se desejar alterar o patrocinador, informe um novo ID:");
            patrocinadorController.listarPatrocinadores();
            int idPatrocinador = view.lerIdPatrocinador();
            Patrocinador patrocinador = patrocinadorController.buscarPorId(idPatrocinador);

            if (patrocinador != null) {
                proposta.setPatrocinador(patrocinador);
            }

            ArquivoUtil.salvarArquivo(propostas, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Proposta atualizada: " + proposta.getTitulo());
            view.mostrarMensagem("Proposta alterada com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Erro ao alterar proposta: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void removerProposta() {
        listarPropostas();
        int id = view.lerId();
        Proposta proposta = buscarPorId(id);

        if (proposta == null) return;

        propostas.remove(id);
        ArquivoUtil.salvarArquivo(propostas, ARQUIVO);

        LogUtil.log(TipoLog.INFO, "Proposta removida: " + proposta.getTitulo());
        view.mostrarMensagem("Proposta excluída com sucesso!");
    }
}

