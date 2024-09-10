package br.com.waproject;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        boolean verbose = false;
        int profundidade = 0;
        String frase = "";

        if (args.length > 0 && args[0].equals("analyze")) {
            for (int i = 1; i < args.length; i++) {
                switch (args[i]) {
                    case "--depth":
                        profundidade = Integer.parseInt(args[++i]);
                        break;
                    case "--verbose":
                        verbose = true;
                        break;
                    default:
                        frase = args[i];
                        break;
                }
            }
        } else {
            System.out.println("Erro: Comando inválido. Use: java -jar cli.jar analyze --depth <n> --verbose (opcional) \"{frase}\"");
            return;
        }

        if (frase.isEmpty()) {
                System.out.println("Erro: Nenhuma frase fornecida.");
                return;
        }

        VerboseLogger logger = new VerboseLogger();

        if (verbose) {
            logger.iniciarCarregamento();
        }

        Map<String, Object> hierarquia = CarregadorJson.leitorJson("dicts/hierarquia.json");

        if (verbose) {
            logger.finalizarCarregamento();
        }

        if (hierarquia != null) {
            System.out.println("Hierarquia carregada com sucesso!");

            if (verbose) {
                logger.iniciarAnalise();
            }

            AnaliseHierarquia analise = new AnaliseHierarquia(hierarquia);
            Map<String, Integer> resultadoAnalise = analise.analisar(frase, profundidade);

            if (verbose) {
                logger.finalizarAnalise();
            }

            System.out.println("Resultados da Análise:");
            for (Map.Entry<String, Integer> entry : resultadoAnalise.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
            if (verbose) {
                logger.exibirTempos();
            }
        } else {
            System.out.println("Falha ao carregar a hierarquia.");
        }
    }

}