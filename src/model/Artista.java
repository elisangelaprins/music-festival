package model;

import model.abstratas.Pessoa;
import model.enums.TipoArtista;
import model.enums.TipoDocumento;
import model.interfaces.Credenciavel;

public class Artista extends Pessoa implements Credenciavel {
    private static final long serialVersionUID = 1L;

    private String nomeArtistico;
    private String generoMusical;
    private TipoArtista tipoArtista;
    private String contatoTelefonico;

    public Artista (String nome, TipoDocumento tipoDocumento, String documento, TipoArtista tipoArtista, String nomeArtistico,  String generoMusical, String contatoTelefonico ) {
        super(nome, tipoDocumento, documento);
        setTipoArtista(tipoArtista);
        setNomeArtistico(nomeArtistico);
        setGeneroMusical(generoMusical);
        setContatoTelefonico(contatoTelefonico);
       }

    public Artista (int id, String nome, TipoDocumento tipoDocumento, String documento, TipoArtista tipoArtista, String nomeArtistico,  String generoMusical, String contatoTelefonico){
        super(id, nome, tipoDocumento, documento);
        setTipoArtista(tipoArtista);
        setNomeArtistico(nomeArtistico);
        setGeneroMusical(generoMusical);
        setContatoTelefonico(contatoTelefonico);
    }

    public TipoArtista getTipoArtista() {
        return tipoArtista;
    }

    public void setTipoArtista(TipoArtista tipoArtista) {
        if (tipoArtista == null) {
            throw new IllegalArgumentException("O tipo de artista não pode ser nulo.");
        }
        this.tipoArtista = tipoArtista;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        if(nomeArtistico == null || nomeArtistico.isEmpty()) {
            throw new IllegalArgumentException("O nome artístico não pode ser vazio.");
        }
        this.nomeArtistico = nomeArtistico;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        if (generoMusical == null || generoMusical.isEmpty()) {
            throw new IllegalArgumentException("O gênero musical não pode ser vazio.");
        }
        this.generoMusical = generoMusical;
    }

    public String getContatoTelefonico() {
        return contatoTelefonico;
    }

    public void setContatoTelefonico(String contatoTelefonico) {
        if (contatoTelefonico == null || contatoTelefonico.isEmpty()) {
            throw new IllegalArgumentException("O contato telefônico não pode ser vazio.");
        }
        if (contatoTelefonico.length() < 10) {
            throw new IllegalArgumentException("Contato telefônico deve ter pelo menos 8 dígitos");
        }
        this.contatoTelefonico = contatoTelefonico;

    }

    @Override
    public String getTipo() {
        return "ARTISTA";
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--------------------------------");
        System.out.println(getTipo());
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Documento (" +getTipoDocumento() + "): " + getDocumento());
        System.out.println("Tipo: " + getTipoArtista());
        System.out.println("Nome Artístico: " + getNomeArtistico());
        System.out.println("Gênero Musical: " + getGeneroMusical());
        System.out.println("Contato: " + getContatoTelefonico());
        System.out.println();
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: " + getTipoArtista() + " | Nome Artístico: " + getNomeArtistico() + " | Gênero Musical: " + getGeneroMusical() + " | Contato telefônico: " + getContatoTelefonico();
    }

    @Override
    public String gerarCredencial() {
        return "ARTISTA-" + getId();
    }

    @Override
    public boolean possuiCredencial() {
        return true;
    }
}