package br.com.waproject;

public class VerboseLogger {

    private long tempoCarregamentoInicio;
    private long tempoAnaliseInicio;
    private long tempoCarregamentoTotal;
    private long tempoAnaliseTotal;

    public void iniciarCarregamento() {
        tempoCarregamentoInicio = System.currentTimeMillis();
    }

    public void finalizarCarregamento() {
        tempoCarregamentoTotal = System.currentTimeMillis() - tempoCarregamentoInicio;
    }

    public void iniciarAnalise() {
        tempoAnaliseInicio = System.currentTimeMillis();
    }

    public void finalizarAnalise() {
        tempoAnaliseTotal = System.currentTimeMillis() - tempoAnaliseInicio;
    }

    public void exibirTempos() {
        System.out.println("Tempo de carregamento dos parâmetros: " + tempoCarregamentoTotal + " ms");
        System.out.println("Tempo de verificação da frase: " + tempoAnaliseTotal + " ms");
    }
}
