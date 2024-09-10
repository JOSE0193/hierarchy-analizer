package com.waproject.analisador;

import br.com.waproject.AnaliseHierarquia;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AnalisadorHierarquiaTest {

    @Test
    public void testAnalisarFraseComSucesso() {
        Map<String, Object> hierarquia = new HashMap<>();
        Map<String, Object> mamiferos = new HashMap<>();
        mamiferos.put("Felinos", List.of("Leões", "Tigres", "Jaguars", "Leopardos"));
        hierarquia.put("Animais", Map.of("Mamíferos", mamiferos));

        AnaliseHierarquia analise = new AnaliseHierarquia(hierarquia);

        Map<String, Integer> resultado = analise.analisar("Eu vi leões e tigres", 3);

        assertEquals(2, resultado.get("Felinos"));
        assertNull(resultado.get("Herbívoros"));
    }

    @Test
    public void testAnalisarFraseSemCorrespondencia() {
        Map<String, Object> hierarquia = new HashMap<>();
        Map<String, Object> mamiferos = new HashMap<>();
        mamiferos.put("Felinos", List.of("Leões", "Tigres"));
        hierarquia.put("Animais", Map.of("Mamíferos", mamiferos));

        AnaliseHierarquia analise = new AnaliseHierarquia(hierarquia);

        Map<String, Integer> resultado = analise.analisar("Eu vi elefantes", 3);

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testAnalisarFraseComProfundidadeErrada() {
        Map<String, Object> hierarquia = new HashMap<>();
        Map<String, Object> mamiferos = new HashMap<>();
        mamiferos.put("Felinos", List.of("Leões", "Tigres"));
        hierarquia.put("Animais", Map.of("Mamíferos", mamiferos));

        AnaliseHierarquia analise = new AnaliseHierarquia(hierarquia);

        Map<String, Integer> resultado = analise.analisar("Eu vi leões", 1);

        assertTrue(resultado.isEmpty());
    }

}
