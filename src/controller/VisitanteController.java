package controller;

import model.Visitante;
import model.abstratas.Pessoa;
import model.enums.TipoDocumento;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;
import view.VisitanteView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitanteController {
    private static final String ARQUIVO = "visitantes.dat";
    VisitanteView view;
    HashMap<Integer, Visitante> visitantes;
    public VisitanteController(VisitanteView view) {
        visitantes = new HashMap<>();
        this.view = view;

        try {
            Map<Integer, ?> carregado =  ArquivoUtil.carregarArquivo(ARQUIVO);

            if (carregado != null) {
                this.visitantes = (HashMap<Integer, Visitante>) carregado;
                for (Visitante v : this.visitantes.values()) {
                    Pessoa.atualizarContador(v.getId());
                }
            }
            LogUtil.log(TipoLog.INFO,"Arquivo de visitantes carregados com sucesso.");
        } catch (Exception e) {
            LogUtil.log(TipoLog.AVISO,"Nenhum arquivo de dados prévio encontrado.");
        }
    }

    public void cadastrarVisitante() {
        try {
            String nome = view.lerNome();
            TipoDocumento tipoDocumento = view.lerTipoDocumento();
            String documento = view.lerDocumento(tipoDocumento);

            for (Visitante v : visitantes.values()) {
                if (v.getDocumento().equalsIgnoreCase(documento)) {
                    throw new IllegalArgumentException("Documento já cadastrado!");
                }
            }

            String email = view.lerEmail();
            String telefone = view.lerTelefone();

            Visitante novoVisitante = new Visitante(nome, tipoDocumento, documento, email, telefone);

            visitantes.put(novoVisitante.getId(), novoVisitante);

            ArquivoUtil.salvarArquivo(visitantes, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Visitante cadastrado: " + novoVisitante.getNome());

            view.mostrarMsg("Visitante cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao cadastrar visitante: " + e.getMessage());

            view.mostrarMsg("Erro ao cadastrar visitante: " + e.getMessage());
        }
    }

    public List<Visitante> getVisitantes() {
        return new ArrayList<>(visitantes.values());
    }

    public void listarVisitantes() {
        view.exibirVisitantes(getVisitantes());
    }

    public void buscarPorDocumento() {
        boolean encontrado = false;
        TipoDocumento tipoDocumento = view.lerTipoDocumento();
        String documento = view.lerDocumento(tipoDocumento);

        if (visitantes.isEmpty()) {
            System.out.println("Nenhum visitante encontrado.");
            return;
        }
        for (Visitante v : visitantes.values()) {
            if (v.getDocumento().equalsIgnoreCase(documento)) {
                encontrado = true;
                v.exibirDetalhes();
            }
        }
        if (!encontrado) {
            view.mostrarMsg("Visitante de " +tipoDocumento+ ": " +documento+ " não foi encontrado.");
        }

        LogUtil.log(TipoLog.INFO, "Busca por visitante realizada: " +tipoDocumento+ " " +documento);
    }

    public void alterarVisitante() {
        listarVisitantes();
        int id = view.lerId();
        view.limparBuffer();

        Visitante visitante = visitantes.get(id);

        if (visitante == null) {
            view.mostrarMsg("Visitante de id " +id+ " não encontrado.");
            return;
        }

        try {
            String nome =  view.lerNome();
            visitante.setNome(nome);
            String email = view.lerEmail();
            visitante.setEmail(email);
            String telefone = view.lerTelefone();
            visitante.setTelefone(telefone);

            ArquivoUtil.salvarArquivo(visitantes, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Visitante alterado: " + visitante.getNome());

            view.mostrarMsg("Visitante alterado com sucesso!");
        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao alterar visitante: " + e.getMessage());

            view.mostrarMsg("Erro: " +e.getMessage());
        }
    }

    public void removerVisitante() {
        listarVisitantes();
        int id = view.lerId();
        view.limparBuffer();
        Visitante visitante = visitantes.get(id);

        if (visitante == null) {
            view.mostrarMsg("Visitante de id " +id+ " não encontrado.");
            return;
        }

        visitantes.remove(id);

        ArquivoUtil.salvarArquivo(visitantes, ARQUIVO);

        LogUtil.log(TipoLog.INFO, "Visitante removido:  " + visitante.getNome());

        view.mostrarMsg("Visitante removido com sucesso!");
    }
}
