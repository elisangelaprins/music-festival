package model;
import model.abstratas.Apresentacao;
import model.enums.TipoShow;

public class Show extends Apresentacao {
    private static final long serialVersionUID = 1L;

    private TipoShow tipoShow;
    private double cache;

    public Show(String nomeApresentacao, String data, String hora, int duracaoMinutos, Artista artista, TipoShow tipoShow, double cache) {
        super(nomeApresentacao, data, hora, duracaoMinutos, artista);
        setTipoShow(tipoShow);
        setCache(cache);
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

    public double getCache() {
        return cache;
    }

    public void setCache(double cache) {
        if (cache <= 0) {
            throw new IllegalArgumentException("Erro: Cachê do show deve ser maior que zero.");
        }
        this.cache = cache;
    }

    @Override
    public String getTipo() {
        return "SHOW";
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("-----------------------------------");
        System.out.println(getTipo());
        System.out.println("ID: " + getId());
        System.out.println("Nome da apresentação: " + getNomeApresentacao() + " | Tipo de Show: " + getTipoShow());
        System.out.println("Tipo do Artista: " + getArtista().getTipoArtista());
        System.out.println("Nome Artístico: " + getArtista().getNomeArtistico());
        System.out.println("Gênero Musical: " + getArtista().getGeneroMusical());
        System.out.println("Data: " + getData() + " | Hora: " + getHora() + " | Duração: " + getDuracaoMinutos() + " min");
        System.out.println("Cachê: R$ " + getCache());
        System.out.println();
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo Show: " + tipoShow + " | Cachê: R$ " + cache;
    }
}
