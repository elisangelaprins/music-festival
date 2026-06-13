package controller;

import model.Staff;
import model.enums.TipoDocumento;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;
import view.StaffView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffController {
    private static final String ARQUIVO = "staff.dat";
    private final StaffView view;
    private HashMap<Integer, Staff> staffs;

    public StaffController(StaffView view) {
        this.view = view;
        this.staffs = new HashMap<>();

        try {
            Map<Integer, ?> carregado = ArquivoUtil.carregarArquivo(ARQUIVO);

            if (carregado != null) {
                this.staffs = (HashMap<Integer, Staff>) carregado;
            }

            LogUtil.log(TipoLog.INFO, "Arquivo de staff carregado com sucesso.");
        } catch (Exception e) {
            LogUtil.log(TipoLog.AVISO, "Nenhum arquivo de dados prévio encontrado.");
        }
    }

    public void cadastrarStaff() {
        try {
            String nome = view.lerNome();
            TipoDocumento tipoDocumento = view.lerTipoDocumento();
            String documento = view.lerDocumento(tipoDocumento);

            for (Staff s : staffs.values()) {
                if (s.getDocumento().equalsIgnoreCase(documento)) {
                    throw new IllegalArgumentException("Documento já cadastrado!");
                }
            }

            String cargo = view.lerCargo();
            String areaAtuacao = view.lerAreaAtuacao();

            Staff novoStaff = new Staff(nome, tipoDocumento, documento, cargo, areaAtuacao);

            staffs.put(novoStaff.getId(), novoStaff);

            ArquivoUtil.salvarArquivo(staffs, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Staff cadastrado: " + novoStaff.getNome());

            view.mostrarMensagem("Staff cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao cadastrar staff: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void listarStaffs() {
        view.exibirStaffs(getStaffs());
    }

    public List<Staff> getStaffs() {
        return new ArrayList<>(staffs.values());
    }

    public Staff buscarPorId(int id) {
        Staff staff = staffs.get(id);

        if (staff == null) {
            view.mostrarMensagem("Staff não encontrado.");
        }

        return staff;
    }

    public void alterarStaff() {
        listarStaffs();
        int id = view.lerId();
        Staff staff = buscarPorId(id);

        if (staff == null) {
            return;
        }

        try {
            String nome = view.lerNome();
            staff.setNome(nome);

            String cargo = view.lerCargo();
            staff.setCargo(cargo);

            String areaAtuacao = view.lerAreaAtuacao();
            staff.setAreaAtuacao(areaAtuacao);

            ArquivoUtil.salvarArquivo(staffs, ARQUIVO);

            LogUtil.log(TipoLog.INFO, "Staff alterado: " + staff.getNome());

            view.mostrarMensagem("Staff alterado com sucesso!");

        } catch (IllegalArgumentException e) {
            LogUtil.log(TipoLog.ERRO, "Falha ao alterar staff: " + e.getMessage());
            view.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    public void removerStaff() {
        listarStaffs();
        int id = view.lerId();
        Staff staff = buscarPorId(id);

        if (staff == null) {
            return;
        }

        staffs.remove(id);

        ArquivoUtil.salvarArquivo(staffs, ARQUIVO);

        LogUtil.log(TipoLog.INFO, "Staff removido: " + staff.getNome());

        view.mostrarMensagem("Staff removido com sucesso!");
    }
}
