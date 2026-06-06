package controller;
import java.util.ArrayList;
import model.Patrocinador;

public class PatrocinadorController {


    private ArrayList<Patrocinador> patrocinadores;


    public PatrocinadorController() {
        patrocinadores = new ArrayList<>();
    }

    public void adicionarPatrocinador(Patrocinador patrocinador) {
        patrocinadores.add(patrocinador);
    }

    public ArrayList<Patrocinador> listarPatrocinadores() {
        return patrocinadores;
    }

    public Patrocinador buscarPatrocinador(int id) {
        for (Patrocinador patrocinador: patrocinadores) {
            if (patrocinador.getId() == id) {
                return patrocinador;
            }
        }

        return null;
    }

    public boolean atualizarCategoria(int id, String nome, String cnpj, double valorPatrocinio, String categoria) {

        Patrocinador patrocinador = buscarPatrocinador(id);

        if (patrocinador != null) {
            patrocinador.setNome(nome);
            patrocinador.setCnpj(cnpj);
            patrocinador.setValorPatrocinio(valorPatrocinio);
            patrocinador.setCategoria(categoria);

            return true;
        }

        return false;
    }

    public boolean removerPatrocinador(int id) {

        Patrocinador patrocinador = buscarPatrocinador(id);

        if (patrocinador != null) {
            patrocinadores.remove(patrocinador);
            return true;
        }

        return false;
    }

    public double calcularTotalPatrocinio() {

        double total = 0;

        for (Patrocinador patrocinador : patrocinadores) {
            total += patrocinador.getValorPatrocinio();
        }

        return total;
    }


}
