package br.com.waproject;

import com.google.gson.Gson;

import java.io.FileReader;
import java.util.Map;

public class CarregadorJson {

    public static Map<String, Object> leitorJson(String caminhoArquivo) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Gson gson = new Gson();
            Map<String, Object> dados = gson.fromJson(reader, Map.class);
            return dados;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
