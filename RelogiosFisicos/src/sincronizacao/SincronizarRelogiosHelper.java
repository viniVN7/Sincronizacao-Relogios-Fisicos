package sincronizacao;

import java.util.List;

/**
 * @author José Vinícius de Carvalho Oliveira
 */

public class SincronizarRelogiosHelper {

    /**
     * Realiza a criação dos processos (atribuindo seu id e seu tempo inicial) e imprime na tela
     * 
     * @param processos
     */
    public static void CriarProcessos(List<Processo> processos) {
        int tempoInicial = 0;

        System.out.println("Processos inicialmente:");
        for (int i = 0; i < Constantes.NUM_PROCESSOS; i++) {
            tempoInicial = (int) (Math.random() * Constantes.MAX_TIME);
            processos.add(new Processo(i, tempoInicial));
            System.out.println("ID: " + i + " - Tempo: " + tempoInicial);
        }
    }

    /**
     * Calcula o tempo médio entre os processos
     * 
     * @param tempoAtual
     * @param tempos
     * @return
     */
    public static int calcularTempoMedio(int tempoAtual, List<Integer> tempos) {
        int somaTempos = tempoAtual;
        int tempoMedio = 0;

        for (int tempo : tempos) {
            somaTempos += tempo;
        }

        tempoMedio = somaTempos / (tempos.size() + 1);
        return tempoMedio;
    }

    /**
     * Atualiza os tempos dos processos
     * 
     * @param processos
     * @param tempoMedio
     */
    public static void atualizarTempos(List<Processo> processos, int tempoMedio) {
        for (Processo processo : processos) {
            processo.setTempo(tempoMedio);
        }
    }

    /**
     * Imprime os processos sincronizados na tela
     * 
     * @param processos
     */
    public static void imprimirProcessosSincronizados(List<Processo> processos) {
        System.out.println("\nProcessos sincronizados:");
        for (Processo processo : processos) {
            System.out.println("ID: " + processo.getId() + " - Tempo: " + processo.getTempo());
        }
    }
}
