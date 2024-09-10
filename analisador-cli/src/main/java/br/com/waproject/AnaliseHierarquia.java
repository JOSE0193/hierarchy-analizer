package br.com.waproject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnaliseHierarquia {

    private final Map<String, Object> hierarquia;

    public AnaliseHierarquia(Map<String, Object> hierarquia) {
        this.hierarquia = hierarquia;
    }

    public Map<String, Integer> analisar(String frase, int profundidade) {
        String[] palavras = frase.toLowerCase().replaceAll("[^a-zà-ú ]", "").split("\\s+");
        Map<String, Integer> resultado = new HashMap<>();
        analisarNivel(hierarquia, palavras, profundidade, 0, resultado);
        return resultado;
    }

    @SuppressWarnings("unchecked")
    private void analisarNivel(Map<String, Object> nivelAtual, String[] palavras, int profundidadeAlvo,
                               int profundidadeAtual, Map<String, Integer> resultado) {
        if (profundidadeAtual > profundidadeAlvo) return;

        for (String chave : nivelAtual.keySet()) {
            Object valor = nivelAtual.get(chave);

            if (valor instanceof List) {
                List<String> subCategorias = (List<String>) valor;
                for (String subCategoria : subCategorias) {
                    String subCategoriaFormatada = subCategoria.toLowerCase();
                    for (String palavra : palavras) {
                        if (subCategoriaFormatada.equals(palavra)) {
                            resultado.put(chave, resultado.getOrDefault(chave, 0) + 1);
                        }
                    }
                }
            }
            if (valor instanceof Map) {
                analisarNivel((Map<String, Object>) valor, palavras, profundidadeAlvo, profundidadeAtual + 1, resultado);
            }
        }
    }

}

