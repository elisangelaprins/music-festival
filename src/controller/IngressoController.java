package controller;

import model.Ingresso;
import model.Visitante;
import util.ArquivoUtil;
import util.LogUtil;
import util.TipoLog;
import view.IngressoView;

import java.util.HashMap;
import java.util.Map;

public class IngressoController {
    private static final String ARQUIVO = "ingressos.dat";
    Map<Integer, Ingresso> ingressos;
    IngressoView ingressoView;

    public IngressoController(IngressoView ingressoView) {
        ingressos = new HashMap<Integer, Ingresso>();
        this.ingressoView = ingressoView;

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
}
