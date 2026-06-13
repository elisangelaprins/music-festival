package controller;
import model.Patrocinador;
import model.enums.CategoriaPatrocinio;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;
import view.PatrocinadorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class PatrocinadorController {
    private static final String ARQUIVO = "patrocinadores.dat";
    private final PatrocinadorView view;
    private HashMap<Integer, Patrocinador> patrocinadores;

    public PatrocinadorController(PatrocinadorView view) {
        this.view = view;
        this.patrocinadores = new HashMap<>();

        try {
            Map<Integer, ?> carregado = ArquivoUtil.carregarArquivo(ARQUIVO);

            if (carregado != null) {
                this.patrocinadores = (HashMap<Integer, Patrocinador>) carregado;
            }

            LogUtil.log(TipoLog.INFO, "Arquivo de patrocinadores carregado com sucesso.");
        } catch (Exception e) {
            LogUtil.log(TipoLog.AVISO, "Nenhum arquivo de dados prévios encontrado para patrocinadores.");
        }
    }

    public void cadastrarPatrocinador() {
        try {
            String nome = view.lerNome();
            String cnpj = view.lerCnpj();

            for (Patrocinador p : patrocinadores.values()) {
                if (p.getCnpj().equals(cnpj)) {
                    throw new IllegalArgumentException("CNPJ já cadastrado!");
                }
            }

            double valorPatrocinio = view.lerValorPatrocinio();
            CategoriaPatrocinio categoria = view.lerCategoriaPatrocinio();

            Patrocinador novoPatrocinador = new Patrocinador(nome, cnpj, valorPatrocinio, categoria);
            patrocinadores.put(novoPatrocinador.getId(), novoPatrocinador);

            ArquivoUtil.salvarArquivo(patrocinadores, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Patrocinador cadastrado: " + novoPatrocinador.getNome());
            view.mostrarMensagem("Patrocinador cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao cadastrar patrocinador: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void listarPatrocinadores() {
        view.exibirPatrocinadores(getPatrocinadores());
    }

    public List<Patrocinador> getPatrocinadores() {
        return new ArrayList<>(patrocinadores.values());
    }

    public Patrocinador buscarPorId(int id) {
        Patrocinador patrocinador = patrocinadores.get(id);
        if (patrocinador == null) {
            view.mostrarMensagem("Patrocinador não encontrado.");
        }
        return patrocinador;
    }

    public void alterarPatrocinador() {
        listarPatrocinadores();
        int id = view.lerId();
        Patrocinador patrocinador = buscarPorId(id);

        if (patrocinador == null){
            return;
        }

        try {
            String nome = view.lerNome();
            patrocinador.setNome(nome);

            String cnpj = view.lerCnpj();
            patrocinador.setCnpj(cnpj);

            double valorPatrocinio = view.lerValorPatrocinio();
            patrocinador.setValorPatrocinio(valorPatrocinio);

            CategoriaPatrocinio categoria = view.lerCategoriaPatrocinio();
            patrocinador.setCategoria(categoria);

            ArquivoUtil.salvarArquivo(patrocinadores, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Patrocinador alterado: " + patrocinador.getNome());
            view.mostrarMensagem("Patrocinador alterado com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao alterar patrocinador: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void removerPatrocinador() {
        listarPatrocinadores();
        int id = view.lerId();
        Patrocinador patrocinador = buscarPorId(id);

        if (patrocinador == null) {
            return;
        }

        patrocinadores.remove(id);
        ArquivoUtil.salvarArquivo(patrocinadores,ARQUIVO);

        LogUtil.log(TipoLog.INFO, "Patrocinador removido: " + patrocinador.getNome());
        view.mostrarMensagem("Patrocinador removido com sucesso!");
    }

    public void mostrarTotalPatrocinado() {
        double total = 0;
        for (Patrocinador patrocinador : patrocinadores.values()) {
            total += patrocinador.getValorPatrocinio();
        }
        view.mostrarMensagem("Total patrocinado: R$ " + total);
    }
}
