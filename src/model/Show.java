package model;
import model.Artista;
import model.Palco;
import model.abstratas.Apresentacao;
import model.enums.TipoShow;

public class Show extends Apresentacao {
    private static final long serialVersionUID = 1L;

    private TipoShow tipoShow;

    public Show (String nomeApresentacao, String data, String hora, int duracaoMinutos, Artista artista, Palco palco, TipoShow tipoShow) {

        super(nomeApresentacao, data, hora, duracaoMinutos, artista, palco);
        setTipoShow(tipoShow);

    }

    public Show(int id, String nomeApresentacao, String data, String hora, int duracaoMinutos, Artista artista, Palco palco, TipoShow tipoShow) {
        super(id, nomeApresentacao, data, hora, duracaoMinutos, artista, palco);
        setTipoShow(tipoShow);
    }

    public TipoShow getTipoShow() {
        return tipoShow;
    }

    public void setTipoShow(TipoShow tipoShow) {
        if (tipoShow == null) {
            throw new IllegalArgumentException("Erro: Tipo de show não pode ser vazio.");
        }
        this.tipoShow = tipoShow;
    }

    @Override
    public String getTipo() {
        return "Show";
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("\n-----------------------------------");
        System.out.println(getTipo());
        System.out.println("ID: " + getId());
        System.out.println("Nome da apresentação: " + getNomeApresentacao() + " | Tipo de Show: " + getTipoShow());
        System.out.println("Tipo do Artista: " + getArtista().getTipoArtista());
        System.out.println("Nome Artístico: " + getArtista().getNomeArtistico());
        System.out.println("Gênero Musical: " + getArtista().getGeneroMusical());
        System.out.println("Data e hora: " + getData() + " , " + getHora() + " | Duração: " + getDuracaoMinutos());
        System.out.println("Palco: " + getPalco().getNome());
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo Show: " + tipoShow;
    }
}
