package controller;

import model.Artista;
import model.Entrevista;
import model.Show;
import model.abstratas.Apresentacao;
import model.enums.TipoShow;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;
import view.ApresentacaoView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApresentacaoController {
    private static final String ARQUIVO = "apresentacao.dat";
    private final ApresentacaoView view;
    private final ArtistaController artistaController;
    private HashMap<Integer, Apresentacao> apresentacoes;

    public ApresentacaoController(ApresentacaoView view, ArtistaController artistaController) {
        this.view = view;
        this.artistaController = artistaController;
        this.apresentacoes = new HashMap<>();

        try {
            Map<Integer, ?> carregado = ArquivoUtil.carregarArquivo(ARQUIVO);

            if (carregado != null) {
                this.apresentacoes = (HashMap<Integer, Apresentacao>) carregado;
            }

            LogUtil.log(TipoLog.INFO, ("Arquivo de apresentações carregados com sucesso."));
        } catch (Exception e) {
            LogUtil.log(TipoLog.AVISO, "Nenhum arquivo de dados prévio encontrado.");
        }
    }

    public void cadastrarShow() {

        try {
            String nomeApresentacao = view.lerNomeApresentacao();
            String data = view.lerData();
            String hora = view.lerHora();
            int duracaoMinutos = view.lerDuracao();
            String nomeArtista = view.lerNomeArtista();
            Artista artista = artistaController.buscarPorNome(nomeArtista);

            if (artista == null) {
                throw new IllegalArgumentException("Artista não encontrado.");
            }
            //falta criar palco aqui
            TipoShow tipoShow = view.lerTipoShow();
            double cache = view.lerCacheShow();

            Apresentacao novaApresentacao = new Show(nomeApresentacao, data, hora, duracaoMinutos, artista, tipoShow, cache);

            apresentacoes.put(novaApresentacao.getId(), novaApresentacao);

            ArquivoUtil.salvarArquivo(apresentacoes, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Apresentação cadastrada: " + novaApresentacao.getNomeApresentacao());

            view.mostrarMensagem("Apresentação cadastrada com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao cadastrar apresentação: " + e.getMessage());

            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void cadastrarEntrevista() {

        try {
            String nomeApresentacao = view.lerNomeEntrevista();
            String nomeEntrevistador = view.lerNomeEntrevistador();
            String tema = view.lerTemaEntrevista();
            String data = view.lerData();
            String hora = view.lerHora();
            int duracaoMinutos = view.lerDuracao();
            String nomeArtista = view.lerNomeArtista();
            Artista artista = artistaController.buscarPorNome(nomeArtista);

            if (artista == null) {
                throw new IllegalArgumentException("Artista não encontrado.");
            }

            //falta criar palco aqui

            Apresentacao novaApresentacao = new Entrevista(nomeApresentacao, nomeEntrevistador, tema, data, hora, duracaoMinutos, artista);

            apresentacoes.put(novaApresentacao.getId(), novaApresentacao);

            ArquivoUtil.salvarArquivo(apresentacoes, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Entrevista cadastrada: " + novaApresentacao.getNomeApresentacao());

            view.mostrarMensagem("Entrevista cadastrada com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao cadastrar entrevista: " + e.getMessage());

            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void listarApresentacoes() {
        view.exibirApresentacoes(getApresentacoes());
    }

    public List<Apresentacao> getApresentacoes() {
        return new ArrayList<>(apresentacoes.values());
    }

    public List<Apresentacao> getShows() {
        List<Apresentacao> shows = new ArrayList<>();
        for (Apresentacao s : apresentacoes.values()) {
            if (s instanceof Show) {
                shows.add(s);
            }
        }
        return shows;
    }

    public List<Apresentacao> getEntrevistas() {
        List<Apresentacao> entrevistas = new ArrayList<>();
        for (Apresentacao a : apresentacoes.values()) {
            if (a instanceof Entrevista) {
                entrevistas.add(a);
            }
        }
        return entrevistas;
    }

    public void listarPorTipo() {
        int opcao = view.lerTipoListagem();
        switch (opcao) {
            case 1:
                view.exibirApresentacoes(getApresentacoes());
                break;
            case 2:
                view.exibirApresentacoes(getShows());
                break;
            case 3:
                view.exibirApresentacoes(getEntrevistas());
                break;
            default:
                view.mostrarMensagem("Opção inválida.");
        }
    }

    public Apresentacao buscarPorId(int id) {
        Apresentacao apresentacao = apresentacoes.get(id);
        if (apresentacao == null) {
            view.mostrarMensagem("Apresentação não encontrada");
        }
        return apresentacao;
    }

    public void exibirPorId() {
        int id = view.lerId();
        Apresentacao apresentacao = apresentacoes.get(id);
        if (apresentacao != null) {
            apresentacao.exibirDetalhes();
        } else {
            LogUtil.log(TipoLog.AVISO, "Apresentação não encontrada. ID: " + id);
            view.mostrarMensagem("Apresentação não encontrada");
        }
    }

    public void buscarPorArtista() {
        String nomeArtista = view.lerNomeArtista();
        List<Apresentacao> encontrados = new ArrayList<>();

        for (Apresentacao a : apresentacoes.values()) {
            if (a.getArtista().getNome().equalsIgnoreCase(nomeArtista) || a.getArtista().getNomeArtistico().equalsIgnoreCase(nomeArtista)) {
                encontrados.add(a);
            }
        }
        if (encontrados.isEmpty()) {
            LogUtil.log(TipoLog.AVISO, "Busca por artista sem resultado: " + nomeArtista);
            view.mostrarMensagem("Nenhuma apresentação encontrada");
            return;
        }
        view.exibirApresentacoes(encontrados);
    }

    public void alterarApresentacao() {
        listarApresentacoes();
        int id = view.lerId();
        Apresentacao apresentacao = buscarPorId(id);

        if (apresentacao == null) {
            return;
        }

        try {
            if (apresentacao instanceof Show) {
                alterarShow((Show) apresentacao);
            } else if (apresentacao instanceof Entrevista) {
                alterarEntrevista((Entrevista) apresentacao);
            }

            ArquivoUtil.salvarArquivo(apresentacoes, ARQUIVO);
            LogUtil.log(TipoLog.INFO, "Apresentação alterada: " + apresentacao.getNomeApresentacao());
            view.mostrarMensagem("Apresentação Alterada com sucesso!");
        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao alterar apresentação: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void removerApresentacao() {
        listarApresentacoes();
        int id = view.lerId();

        Apresentacao apresentacao = buscarPorId(id);

        if (apresentacao == null) {
            return;
        }
        apresentacoes.remove(id);

        ArquivoUtil.salvarArquivo(apresentacoes, ARQUIVO);

        LogUtil.log(TipoLog.INFO, "Apresentação removida: " + apresentacao.getNomeApresentacao());

        view.mostrarMensagem("Apresentação removida com sucesso!");
    }

    private void alterarShow(Show show) {
        String nome = view.lerNomeApresentacao();
        String data = view.lerData();
        String hora = view.lerHora();
        int duracao = view.lerDuracao();
        TipoShow tipoShow = view.lerTipoShow();
        double cache = view.lerCacheShow();

        show.setNomeApresentacao(nome);
        show.setData(data);
        show.setHora(hora);
        show.setDuracaoMinutos(duracao);
        show.setTipoShow(tipoShow);
        show.setCache(cache);
    }

    private void alterarEntrevista(Entrevista entrevista) {
        String nome = view.lerNomeEntrevista();
        String nomeEntrevistador = view.lerNomeEntrevistador();
        String tema = view.lerTemaEntrevista();
        String data = view.lerData();
        String hora = view.lerHora();
        int duracao = view.lerDuracao();

        entrevista.setNomeApresentacao(nome);
        entrevista.setNomeEntrevistador(nomeEntrevistador);
        entrevista.setTemaEntrevista(tema);
        entrevista.setData(data);
        entrevista.setHora(hora);
        entrevista.setDuracaoMinutos(duracao);
    }

    public void mostrarTotalCaches() {
        double total = 0;
        for (Apresentacao a : apresentacoes.values()) {
            if (a instanceof Show) {
                total += ((Show) a).getCache();
            }
        }
        view.mostrarMensagem("Total gasto com cachês de shows: R$ " + total);
    }
}
