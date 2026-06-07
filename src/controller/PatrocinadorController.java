package controller;
import java.util.HashMap;
import java.util.Map;
import model.Patrocinador;
import model.enums.CategoriaPatrocinio;

public class PatrocinadorController {


    private Map<Integer, Patrocinador> patrocinadores;


    public PatrocinadorController() {
        this.patrocinadores = new HashMap<>();
    }

    public void adicionarPatrocinador(Patrocinador patrocinador) {
        patrocinadores.put(patrocinador.getId(),patrocinador);
    }

    public Map<Integer, Patrocinador> listarPatrocinadores() {
        return new HashMap<>(patrocinadores) ;
    }

    public Patrocinador buscarPatrocinador(int id) {
        return patrocinadores.get(id);
    }

    public boolean atualizarPatrocinador(int id, String nome, String cnpj, double valorPatrocinio, CategoriaPatrocinio categoria) {

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

        if (patrocinadores.containsKey(id)){
            patrocinadores.remove(id);

            return true;
        }

        return false;
    }

    public double calcularTotalPatrocinio() {

        double total = 0;

        for (Patrocinador patrocinador : patrocinadores.values()) {
            total += patrocinador.getValorPatrocinio();
        }

        return total;
    }


}
