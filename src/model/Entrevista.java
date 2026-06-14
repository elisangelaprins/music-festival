package model;
import model.abstratas.Apresentacao;

public class Entrevista extends Apresentacao {
    private static final long serialVersionUID = 1L;

    private String nomeEntrevistador;
    private String temaEntrevista;

    public Entrevista(String nomeApresentacao, String nomeEntrevistador, String temaEntrevista, String data, String hora, int duracaoMinutos, Artista artista) {

        super(nomeApresentacao, data, hora, duracaoMinutos, artista);

        setNomeEntrevistador(nomeEntrevistador);
        setTemaEntrevista(temaEntrevista);
    }

    public Entrevista(int id, String nomeApresentacao, String nomeEntrevistador, String temaEntrevista, String data, String hora, int duracaoMinutos, Artista artista) {

        super(id, nomeApresentacao, data, hora, duracaoMinutos, artista);

        setNomeEntrevistador(nomeEntrevistador);
        setTemaEntrevista(temaEntrevista);
    }

    public String getNomeEntrevistador() {
        return nomeEntrevistador;
    }

    public void setNomeEntrevistador(String nomeEntrevistador) {
        if (nomeEntrevistador == null || nomeEntrevistador.isEmpty()){
            throw new IllegalArgumentException("Erro: Tema da entrevista não pode ser vazio.");
        }
        this.nomeEntrevistador = nomeEntrevistador;
    }

    public String getTemaEntrevista() {
        return temaEntrevista;
    }

    public void setTemaEntrevista(String temaEntrevista) {
        if (temaEntrevista == null || temaEntrevista.isEmpty()) {
            throw new IllegalArgumentException("Erro: Nome da entrevista não pode ser vazio.");
        }
        this.temaEntrevista = temaEntrevista;
    }

    @Override
    public String getTipo() {
        return "ENTREVISTA";
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("-----------------------------------");
        System.out.println(getTipo());
        System.out.println("ID: " + getId());
        System.out.println("Nome da Entrevista: " + getNomeApresentacao());
        System.out.println("Nome do Entrevistador: " + getNomeEntrevistador());
        System.out.println("Tema da entrevista: " + getTemaEntrevista());
        System.out.println("Tipo do Artista: " + getArtista().getTipoArtista());
        System.out.println("Nome Artístico: " + getArtista().getNomeArtistico());
        System.out.println("Gênero Musical: " + getArtista().getGeneroMusical());
        System.out.println("Data: " + getData() + " | Hora: " + getHora() + " | Duração: " + getDuracaoMinutos() + " min");
        System.out.println();
    }

    @Override
    public String toString() {
        return super.toString() + " | Nome entrevistador: " + nomeEntrevistador + " | Tema da Entrevista: " + temaEntrevista;
    }
}
