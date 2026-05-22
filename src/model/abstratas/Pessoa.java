package model.abstratas;

public abstract class Pessoa {
    private int id;
    private String nome;
    private String documento;
    private int anoNascimento;
    private static int contadorId = 1;

    //Construtor para cadastro de Pessoa
    public Pessoa(String nome, String documento, int anoNascimento) {
        this.id = contadorId++;
        setNome(nome);
        setDocumento(documento);
        setAnoNascimento(anoNascimento);
    }

    //Construtor de sobrecarga para o sistema ler uma pessoa pelo (TXT/JSON)
    public Pessoa(int id, String nome, String documento, int anoNascimento) {
        setId(id); // Usa o ID que veio do arquivo
        setNome(nome);
        setDocumento(documento);
        setAnoNascimento(anoNascimento);

        // para o contador sempre iniciar pelo ultimo id existente
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        if (!documento.isEmpty()) {
            this.documento = documento;
        }
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        if (anoNascimento >= 0) {
            this.anoNascimento = anoNascimento;
        }
    }

    public int calcularIdade(int anoAtual){
        return anoAtual - anoNascimento;
    }

    public abstract String getTipo();

    public abstract void exibirDetalhes();

    @Override
    public String toString() {
        return getTipo() + " | ID: " + id + " | Nome: " + nome + " | Documento: " + documento;
    }
}