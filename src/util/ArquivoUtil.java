package util;

import java.io.*;
import java.util.Map;

public class ArquivoUtil {
    private static final String PASTA = "dados";

    public static void salvarArquivo(Map <Integer, ?> mapa, String nomeArquivo) {
        File pasta = new File(PASTA);

        if (!pasta.exists()) {
            pasta.mkdir();
        }

        try {

            FileOutputStream arquivo = new FileOutputStream(PASTA + File.separator + nomeArquivo);
            ObjectOutputStream gravar = new ObjectOutputStream(arquivo);

            gravar.writeObject(mapa);

            gravar.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public static Map <Integer, ?> carregarArquivo(String nomeArquivo) {

        try {
            FileInputStream file = new FileInputStream(PASTA + File.separator + nomeArquivo);
            ObjectInputStream in = new ObjectInputStream(file);

            Object objetoLido = in.readObject();

            in.close();

            if(objetoLido instanceof Map) {
                return (Map<Integer, ?>) objetoLido;
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }

        return null;
    }

}
