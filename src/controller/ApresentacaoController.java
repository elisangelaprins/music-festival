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

import java.util.*;

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
                for (Apresentacao a : this.apresentacoes.values()) {
                    Apresentacao.atualizarContador(a.getId());
                }
            }

            LogUtil.log(TipoLog.INFO, ("Arquivo de apresentações carregado com sucesso."));
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
        } catch (InputMismatchException e) {
            view.mostrarMensagem("Erro: Insira apenas valores válidos.");
            view.limparBuffer();
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

            Apresentacao novaApresentacao = new Entrevista(nomeApresentacao, nomeEntrevistador, tema, data, hora, duracaoMinutos, artista);

            apresentacoes.put(novaApresentacao.getId(), novaApresentacao);

            ArquivoUtil.salvarArquivo(apresentacoes, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Entrevista cadastrada: " + novaApresentacao.getNomeApresentacao());

            view.mostrarMensagem("Entrevista cadastrada com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao cadastrar entrevista: " + e.getMessage());

            view.mostrarMensagem("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            view.mostrarMensagem("Erro: Insira apenas valores válidos.");
            view.limparBuffer();
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
        try {
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
        } catch (InputMismatchException e) {
            view.mostrarMensagem("Erro: Insira apenas valores válidos.");
            view.limparBuffer();
        }
    }

    public Apresentacao buscarPorId(int id) {
        Apresentacao apresentacao = apresentacoes.get(id);
        if (apresentacao == null) {
            LogUtil.log(TipoLog.AVISO, "Apresentação não encontrada. ID: " + id);
            view.mostrarMensagem("Apresentação não encontrada");
        }
        return apresentacao;
    }

    public void exibirPorId() {
        try {
            int id = view.lerId();
            Apresentacao apresentacao = buscarPorId(id);
            if (apresentacao != null) {
                apresentacao.exibirDetalhes();
            }
        } catch (InputMismatchException e) {
            view.mostrarMensagem("Erro: Insira apenas valores válidos.");
            view.limparBuffer();
        }
    }

    public void buscarPorArtista() {
        try {
            String nomeArtista = view.lerNomeArtista();
            List<Apresentacao> encontrados = new ArrayList<>();

            for (Apresentacao a : apresentacoes.values()) {
                if (a.getArtista() !=null && (a.getArtista().getNome().equalsIgnoreCase(nomeArtista) || a.getArtista().getNomeArtistico().equalsIgnoreCase(nomeArtista))) {
                    encontrados.add(a);
                }
            }
            if (encontrados.isEmpty()) {
                LogUtil.log(TipoLog.AVISO, "Busca por artista sem resultado: " + nomeArtista);
                view.mostrarMensagem("Nenhuma apresentação encontrada");
                return;
            }
            view.exibirApresentacoes(encontrados);
            LogUtil.log(TipoLog.INFO, "Busca por artista realizada: " + nomeArtista);
        } catch (InputMismatchException e) {
            view.mostrarMensagem("Erro: Insira apenas valores válidos.");
            view.limparBuffer();
        }
    }

    public void alterarApresentacao() {
        try {
            listarApresentacoes();
            int id = view.lerId();
            Apresentacao apresentacao = buscarPorId(id);

            if (apresentacao == null) {
                return;
            }
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
        } catch (InputMismatchException e) {
            view.mostrarMensagem("Erro: Insira apenas valores válidos.");
            view.limparBuffer();
        }
    }

    public void removerApresentacao() {
        try {
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
        } catch (InputMismatchException e) {
            view.mostrarMensagem("Erro: Insira apenas valores válidos.");
            view.limparBuffer();
        }
    }

    public void mostrarTotalCaches() {
        double total = 0;
        for (Apresentacao a : apresentacoes.values()) {
            if (a instanceof Show) {
                total += ((Show) a).getCache();
            }
        }
        if (total > 0) {
            LogUtil.log(TipoLog.INFO,"Cálculo de cachês realizado. Total: R$ " + total);
            view.mostrarMensagem("\n === Total gasto com cachês de shows: R$ " + total + " ===\n");
        } else {
            LogUtil.log(TipoLog.AVISO,"Tentativa de mostrar cachês, mas nenhum valor de show foi registrado.");
            view.mostrarMensagem("Nenhum show com cachê cadastrado até o momento.\n");
        }

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
}