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

    public boolean atualizarCategoria(int id, String categoria) {

        Patrocinador patrocinador = buscarPatrocinador(id);

        if (patrocinador != null) {
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


}
