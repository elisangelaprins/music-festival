package model.enums;

public enum TipoDocumento {
    CPF(11),
    PASSAPORTE(8);

    private final int tamanhoDocumento;

    TipoDocumento(int tamanhoDocumento){
        this.tamanhoDocumento = tamanhoDocumento;
    }

    public void validar(String documento){

        if (documento == null || documento.isEmpty()) {
            throw new IllegalArgumentException("Documento não pode ser vazio.");
        }

        if (documento.length() != tamanhoDocumento) {
            throw new IllegalArgumentException(
                    name() + " deve conter exatamente " + tamanhoDocumento + " caracteres."
            );
        }
    }
}
