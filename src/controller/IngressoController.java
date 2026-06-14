package controller;

import model.Ingresso;
import model.Visitante;
import model.abstratas.Apresentacao;
import model.enums.TipoIngresso;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;
import view.IngressoView;

import java.util.*;

public class IngressoController {
    private static final String ARQUIVO = "ingressos.dat";
    Map<Integer, Ingresso> ingressos;
    IngressoView view;

    public IngressoController(IngressoView view) {
        ingressos = new HashMap<Integer, Ingresso>();
        this.view = view;

        try {
            Map<Integer, ?> carregado =  ArquivoUtil.carregarArquivo(ARQUIVO);

            if (carregado != null) {
                this.ingressos = (Map<Integer, Ingresso>) carregado;
                for (Ingresso i : ingressos.values()) {
                    Ingresso.atualizarContador(i.getId());
                }
            }
            LogUtil.log(TipoLog.INFO,"Arquivo de ingressos carregados com sucesso.");
        } catch (Exception e) {
            LogUtil.log(TipoLog.AVISO,"Nenhum arquivo de dados prévio encontrado.");
        }
    }

    public void cadastrarIngresso(List<Visitante> visitantes, List<Apresentacao> apresentacoes) {
        try {

            TipoIngresso tipoIngresso = view.lerTipoIngresso();

            if (visitantes.isEmpty()) {
                view.mostrarMsg("Nenhum visitante encontrado.");
                return;
            }
            for (Visitante v : visitantes) {
                v.exibirDetalhes();
            }
            int idVisitante = view.lerIdVisitante();
            view.limparBuffer();
            Visitante visitante = null;
            for (Visitante v : visitantes) {
                if (v.getId() ==  idVisitante) {
                    visitante = v;
                    break;
                }
            }
            if (visitante == null) {
                view.mostrarMsg("Visitante de id " +idVisitante+ " não encontrado.");
                return;
            }

            if (apresentacoes.isEmpty()) {
                view.mostrarMsg("Nenhuma apresentação encontrada.");
                return;
            }
            for (Apresentacao a : apresentacoes) {
                a.exibirDetalhes();
            }
            int idApresentacao = view.lerIdApresentacao();
            view.limparBuffer();
            Apresentacao apresentacao = null;
            for (Apresentacao a : apresentacoes) {
                if (a.getId() ==  idApresentacao) {
                    apresentacao = a;
                    break;
                }
            }
            if (apresentacao == null) {
                view.mostrarMsg("Apresentação de id " +idVisitante+ " não encontrada.");
                return;
            }

            double valor = view.lerValor();
            double valorFinal;
            view.limparBuffer();

            Ingresso novoIngresso;

            switch (tipoIngresso) {
                case INTEIRA:
                    valorFinal = emitirIngresso(valor);
                    novoIngresso = new Ingresso(tipoIngresso, visitante, apresentacao, valor, valorFinal);
                    break;
                case MEIA:
                    String rgm = view.lerRGM();
                    valorFinal = emitirIngresso(valor, rgm);
                    novoIngresso = new Ingresso(tipoIngresso, visitante, apresentacao, valor, valorFinal, rgm);
                    break;
                case VIP:
                    double taxaVip = view.lerTaxaVip();
                    valorFinal = emitirIngresso(valor, taxaVip);
                    novoIngresso = new Ingresso(tipoIngresso, visitante, apresentacao, valor, valorFinal);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo do ingresso inválido.");
            }

            ingressos.put(novoIngresso.getId(), novoIngresso);

            ArquivoUtil.salvarArquivo(ingressos, ARQUIVO);

            LogUtil.log(TipoLog.INFO,"Ingresso cadastrado com sucesso: id " + novoIngresso.getId());

            view.mostrarMsg("Ingresso cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO,"Falha ao cadastrar ingresso: "+e.getMessage());

            view.mostrarMsg("Falha ao cadastrar ingresso: "+e.getMessage());
        } catch (InputMismatchException e) {
            view.mostrarMsg("Insira um valor válido.");
            view.limparBuffer();
        }

    }

    public List<Ingresso> getIngressos() {
        return new ArrayList<Ingresso>(ingressos.values());
    }

    public void listarIngressos(){
        view.exibirIngressos(getIngressos());
    }

    public void buscarPorApresentacao() {
        try {
            ArrayList<Ingresso> ingressosEncontrados = new ArrayList<>();
            int idApresentacao = view.lerIdApresentacao();
            view.limparBuffer();

            for (Ingresso i : ingressos.values()) {
                Apresentacao apresentacaoFiltro = i.getApresentacao();
                if (apresentacaoFiltro.getId() == idApresentacao) {
                    ingressosEncontrados.add(i);
                }
            }

            if (ingressosEncontrados.isEmpty()) {
                view.mostrarMsg("Nenhum ingresso encontrado.");
                return;
            }

            for (Ingresso i : ingressosEncontrados) {
                i.exibirDetalhes();
            }
            LogUtil.log(TipoLog.INFO,"Busca por ingresso realizada: Apresentação id " +idApresentacao);
        } catch (InputMismatchException e) {
            view.mostrarMsg("Insira um valor válido.");
            view.limparBuffer();
        }
    }

    public void buscarPorVisitante() {
        try {
            ArrayList<Ingresso> ingressosEncontrados = new ArrayList<>();
            int idVisitante = view.lerIdVisitante();
            view.limparBuffer();

            for (Ingresso i : ingressos.values()) {
                Visitante visitanteFiltro = i.getVisitante();
                if (visitanteFiltro.getId() == idVisitante) {
                    ingressosEncontrados.add(i);
                }
            }

            if (ingressosEncontrados.isEmpty()) {
                view.mostrarMsg("Nenhum ingresso encontrado.");
                return;
            }

            for (Ingresso i : ingressosEncontrados) {
                i.exibirDetalhes();
            }
            LogUtil.log(TipoLog.INFO,"Busca por ingresso realizada: Visitante id " +idVisitante);
        } catch (InputMismatchException e) {
            view.mostrarMsg("Insira um valor válido.");
            view.limparBuffer();
        }
    }

    public void alterarIngresso(List<Visitante> visitantes) {
        try {
            listarIngressos();
            int id = view.lerIdIngresso();
            view.limparBuffer();

            Ingresso ingressoAlterado = ingressos.get(id);

            if (ingressoAlterado == null) {
                view.mostrarMsg("Ingresso de id " +id+ " não encontrado.");
                return;
            }

            TipoIngresso tipoIngresso = view.lerTipoIngresso();

            if (visitantes.isEmpty()) {
                view.mostrarMsg("Nenhum visitante encontrado.");
                return;
            }
            for (Visitante v : visitantes) {
                v.exibirDetalhes();
            }
            int idVisitante = view.lerIdVisitante();
            view.limparBuffer();
            Visitante visitante = null;
            for (Visitante v : visitantes) {
                if (v.getId() ==  idVisitante) {
                    visitante = v;
                    break;
                }
            }
            if (visitante == null) {
                view.mostrarMsg("Visitante de id " +idVisitante+ " não encontrado.");
                return;
            }

            double valor = view.lerValor();
            double valorFinal;

            view.limparBuffer();

            switch (tipoIngresso) {
                case INTEIRA:
                    valorFinal = emitirIngresso(valor);
                    ingressoAlterado.setTipoIngresso(TipoIngresso.INTEIRA);
                    ingressoAlterado.setVisitante(visitante);
                    ingressoAlterado.setValor(valor);
                    ingressoAlterado.setValorFinal(valorFinal);
                    break;
                case MEIA:
                    String rgm = view.lerRGM();
                    valorFinal = emitirIngresso(valor, rgm);
                    ingressoAlterado.setTipoIngresso(TipoIngresso.MEIA);
                    ingressoAlterado.setVisitante(visitante);
                    ingressoAlterado.setValor(valor);
                    ingressoAlterado.setValorFinal(valorFinal);
                    ingressoAlterado.setRgm(rgm);
                    break;
                case VIP:
                    double taxaVip = view.lerTaxaVip();
                    valorFinal = emitirIngresso(valor, taxaVip);
                    ingressoAlterado.setTipoIngresso(TipoIngresso.VIP);
                    ingressoAlterado.setVisitante(visitante);
                    ingressoAlterado.setValor(valor);
                    ingressoAlterado.setValorFinal(valorFinal);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo do ingresso inválido.");
            }

            ingressos.put(ingressoAlterado.getId(), ingressoAlterado);

            ArquivoUtil.salvarArquivo(ingressos, ARQUIVO);

            LogUtil.log(TipoLog.INFO,"Ingresso alterado: id " + ingressoAlterado.getId());

            view.mostrarMsg("Ingresso alterado com sucesso!");
        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO,"Falha ao alterar ingresso: "+e.getMessage());

            view.mostrarMsg("Erro: " +e.getMessage());
        } catch (InputMismatchException e) {
            view.mostrarMsg("Insira um valor válido.");
        }
    }

    public void removerIngresso() {
        Ingresso ingresso = null;
        listarIngressos();
        try {
            int id = view.lerIdIngresso();
            view.limparBuffer();
            ingresso = ingressos.get(id);

            if (ingresso == null) {
                view.mostrarMsg("Ingresso de id " +id+ " não encontrado.");
                return;
            }

            ingressos.remove(id);

            ArquivoUtil.salvarArquivo(ingressos, ARQUIVO);

            LogUtil.log(TipoLog.INFO,"Ingresso removido: id " +ingresso.getId());

            view.mostrarMsg("Ingresso removido com sucesso!");
        } catch (InputMismatchException e) {
            view.mostrarMsg("Insira um valor válido");
        }
    }

    private double emitirIngresso(double valor) {
        return valor;
    }
    private double emitirIngresso(double valor, String rgm) {
        return valor*0.5;
    }
    private double emitirIngresso(double valor, double taxaVip) {
        return valor+taxaVip;
    }
}
