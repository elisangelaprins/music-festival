package model;

import model.abstratas.Pessoa;
import model.enums.TipoArtista;
import model.enums.TipoDocumento;

public class Artista extends Pessoa {
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
            throw new IllegalArgumentException("Tipo de Artista inválido");
        }
        this.tipoArtista = tipoArtista;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        if(nomeArtistico == null || nomeArtistico.isEmpty()) {
            throw new IllegalArgumentException("Nome artístico inválido");
        }
        this.nomeArtistico = nomeArtistico;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        if (generoMusical == null || generoMusical.isEmpty()) {
            throw new IllegalArgumentException("Gênero musical inválido");
        }
        this.generoMusical = generoMusical;
    }

    public String getContatoTelefonico() {
        return contatoTelefonico;
    }

    public void setContatoTelefonico(String contatoTelefonico) {
        if (contatoTelefonico == null || contatoTelefonico.isEmpty()) {
            throw new IllegalArgumentException("Contato telefônico inválido");
        }
        if (contatoTelefonico.length() < 8) {
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
        System.out.println("\n--------------------------------");
        System.out.println(getTipo());
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Documento (" +getTipoDocumento() + "): " + getDocumento());
        System.out.println("Tipo: " + getTipoArtista());
        System.out.println("Nome Artístico: " + getNomeArtistico());
        System.out.println("Gênero Musical: " + getGeneroMusical());
        System.out.println("Contato: " + getContatoTelefonico());
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Tipo: " + getTipoArtista()
                + "\n Nome Artístico: " + getNomeArtistico()
                + "\n Gênero Musical: " + getGeneroMusical()
                + "\n Contato telefônico: " + getContatoTelefonico();
    }
}