package controller;

import model.Artista;
import model.Credencial;
import model.Staff;
import model.interfaces.Credenciavel;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;
import view.CredencialView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CredencialController {

    private static final String ARQUIVO = "credenciais.dat";
    private final CredencialView view;
    private final StaffController staffController;
    private final ArtistaController artistaController;
    private HashMap<Integer, Credencial> credenciais;

    public CredencialController(CredencialView view, StaffController staffController, ArtistaController artistaController) {
        this.view = view;
        this.staffController = staffController;
        this.artistaController = artistaController;
        this.credenciais = new HashMap<>();

        try {
            Map<Integer, ?> carregado = ArquivoUtil.carregarArquivo(ARQUIVO);

            if (carregado != null) {
                this.credenciais = (HashMap<Integer, Credencial>) carregado;
            }

            LogUtil.log(TipoLog.INFO, "Arquivo de credenciais carregado com sucesso.");
        } catch (Exception e) {
            LogUtil.log(TipoLog.AVISO, "Nenhum arquivo de dados prévio encontrado.");
        }
    }


    public void cadastrarCredencial() {
        try {
            int tipoTitular = view.lerTipoTitular();
            Credenciavel titular;

            switch (tipoTitular) {
                case 1:
                    staffController.listarStaffs();
                    int idStaff = view.lerStaffId();
                    Staff staff = staffController.buscarPorId(idStaff);
                    titular = staff;
                    break;
                case 2:
                    artistaController.listarArtistas();
                    int idArtista = view.lerArtistaId();
                    Artista artista = artistaController.buscarPorId(idArtista);
                    titular = artista;
                    break;
                default:
                    view.mostrarMensagem("Tipo de titular inválido.");
                    return;
            }

            if (titular == null) {
                return;
            }

            String tipoAcesso = view.lerTipoAcesso();
            String codigo = titular.gerarCredencial();

            for (Credencial c : credenciais.values()) {
                if (c.getCodigo().equalsIgnoreCase(codigo)) {
                    throw new IllegalArgumentException("Esse titular já possui credencial!");
                }
            }

            Credencial nova = new Credencial(codigo, tipoAcesso, titular);

            credenciais.put(nova.getId(), nova);

            ArquivoUtil.salvarArquivo(credenciais, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Credencial cadastrada: " + nova.getCodigo());

            view.mostrarMensagem("Credencial cadastrada com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao cadastrar credencial: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }


    public void listarCredenciais() {
        view.exibirCredenciais(getCredenciais());
    }

    public List<Credencial> getCredenciais() {
        return new ArrayList<>(credenciais.values());
    }

    public Credencial buscarPorId(int id) {
        Credencial credencial = credenciais.get(id);

        if (credencial == null) {
            view.mostrarMensagem("Credencial não encontrada.");
        }

        return credencial;
    }

    public void alterarCredencial() {
        listarCredenciais();
        int id = view.lerId();
        Credencial credencial = buscarPorId(id);

        if (credencial == null) {
            return;
        }

        try {
            String tipoAcesso = view.lerTipoAcesso();
            credencial.setTipoAcesso(tipoAcesso);

            ArquivoUtil.salvarArquivo(credenciais, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Credencial alterada: " + credencial.getCodigo());

            view.mostrarMensagem("Credencial alterada com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao alterar credencial: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void removerCredencial() {
        listarCredenciais();
        int id = view.lerId();
        Credencial credencial = buscarPorId(id);

        if (credencial == null) {
            return;
        }

        credenciais.remove(id);

        ArquivoUtil.salvarArquivo(credenciais, ARQUIVO);

        LogUtil.log(TipoLog.INFO, "Credencial removida: " + credencial.getCodigo());

        view.mostrarMensagem("Credencial removida com sucesso!");
    }
}
