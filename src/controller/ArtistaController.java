package controller;

import model.Artista;
import model.abstratas.Pessoa;
import model.enums.TipoArtista;
import model.enums.TipoDocumento;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;
import view.ArtistaView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistaController {
    private static final String ARQUIVO = "artistas.dat";
    private final ArtistaView view;
    private HashMap<Integer, Artista> artistas;

    public ArtistaController(ArtistaView view) {
        this.view = view;
        this.artistas = new HashMap<>();

        try {
            Map<Integer, ?> carregado = ArquivoUtil.carregarArquivo(ARQUIVO);

            if (carregado != null) {
                this.artistas = (HashMap<Integer, Artista>) carregado;
                for (Artista a : this.artistas.values()) {
                    Pessoa.atualizarContador(a.getId());
                }
            }

            LogUtil.log(TipoLog.INFO, ("Arquivo de artistas carregados com sucesso."));
        } catch (Exception e) {
            LogUtil.log(TipoLog.AVISO, "Nenhum arquivo de dados prévio encontrado.");
        }
    }

    public void cadastrarArtista() {
        try {
            String nome = view.lerNome();
            TipoDocumento tipoDocumento = view.lerTipoDocumento();
            String documento = view.lerDocumento(tipoDocumento);

            for (Artista a : artistas.values()) {
                if (a.getDocumento().equalsIgnoreCase(documento)) {
                    throw new IllegalArgumentException("Documento já cadastrado!");
                }
            }
            TipoArtista tipoArtista = view.lerTipoArtista();
            String nomeArtistico = view.lerNomeArtistico(tipoArtista);
            String generoMusical = view.lerGeneroMusical();
            String contatoTelefonico = view.lerContatoTelefonico();


            Artista novoArtista = new Artista(nome, tipoDocumento, documento, tipoArtista, nomeArtistico, generoMusical, contatoTelefonico);

            artistas.put(novoArtista.getId(), novoArtista);

            ArquivoUtil.salvarArquivo(artistas, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Artista cadastrado: " + novoArtista.getNome());

            view.mostrarMensagem("Artista cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao cadastrar artista: " + e.getMessage());

            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void listarArtistas() {
        view.exibirArtistas(getArtistas());
    }

    public void buscarPorNome(){
        String nome = view.lerNomeBusca();

        List<Artista> encontrados = new ArrayList<>();

        for (Artista a : artistas.values()) {
            if (a.getNome().equalsIgnoreCase(nome) || a.getNomeArtistico().equalsIgnoreCase(nome)) {
                encontrados.add(a);
            }
        }

        if (encontrados.isEmpty()) {
            LogUtil.log(TipoLog.AVISO, "Busca por nome sem resultado: " + nome);
            view.mostrarMensagem("Nenhum artista encontrado.");
            return;
        }

        LogUtil.log(TipoLog.INFO, "Busca por nome realizada: " + nome);

        view.exibirArtistas(encontrados);
    }

    public List<Artista> getArtistas() {
        return new ArrayList<>(artistas.values());
    }

    public Artista buscarPorId(int id) {
        Artista artista = artistas.get(id);

        if (artista == null) {
            view.mostrarMensagem("Artista não encontrado");
        }

        return artista;
    }

    public Artista buscarPorNome(String nomeArtista) {
        for (Artista artista : artistas.values()) {
            if (artista.getNome().equalsIgnoreCase(nomeArtista) || artista.getNomeArtistico().equalsIgnoreCase(nomeArtista)) {
                return artista;
            }
        }
        return null;
    }

    public void alterarArtista() {
        listarArtistas();
        int id = view.lerId();
        Artista artista = buscarPorId(id);

        if (artista == null) {
            return;
        }

        try {
            String nome = view.lerNome();
            artista.setNome(nome);

            TipoArtista tipoArtista = view.lerTipoArtista();
            artista.setTipoArtista(tipoArtista);

            String nomeArtistico = view.lerNomeArtistico(tipoArtista);
            artista.setNomeArtistico(nomeArtistico);

            String generoMusical = view.lerGeneroMusical();
            artista.setGeneroMusical(generoMusical);

            String contatoTelefonico = view.lerContatoTelefonico();
            artista.setContatoTelefonico(contatoTelefonico);

            ArquivoUtil.salvarArquivo(artistas, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Artista alterado: " + artista.getNome());

            view.mostrarMensagem("Artista Alterado com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao alterar artista: " + e.getMessage());

            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void removerArtista() {
        listarArtistas();
        int id = view.lerId();
        Artista artista = buscarPorId(id);

        if (artista == null) {
            return;
        }

        artistas.remove(id);

        ArquivoUtil.salvarArquivo(artistas, ARQUIVO);

        LogUtil.log(TipoLog.INFO, "Artista removido: " + artista.getNome());

        view.mostrarMensagem("Artista removido com sucesso!");

    }
}
