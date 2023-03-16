package sincronizacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author José Vinícius de Carvalho Oliveira
 */

public class Main {
    public static void main(String[] args) throws IOException {
        
        List<Processo> processos = new ArrayList<>();
        ServidorTempo servidorTempo = new ServidorTempo();
        int tempoAtualizado = 0;
        int diferencaTempo = 0;

        SincronizarRelogiosHelper.CriarProcessos(processos);

        // Definindo qual será o servidor de tempo
        Processo servidor = processos.get(0);

        Thread threadServidor = new Thread(new Servidor(processos));
        threadServidor.start();

        // Sincronizando os processos
        for (int i = 0; i < Constantes.NUM_ITERACOES; i++) {
            // Aguarda um tempo aleatório antes de solicitar o tempo atual
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread threadCliente = new Thread(new Cliente(servidor, servidorTempo));
            threadCliente.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            diferencaTempo = processos.get(0).getTempo() - servidorTempo.getTempoAtual();
            
            for (Processo processo : processos) {
                if (processo.getId() != 0) {
                    tempoAtualizado = servidorTempo.getTempoAtual() + diferencaTempo;
                    processo.setTempo(tempoAtualizado);
                }
            }
        }

        SincronizarRelogiosHelper.imprimirProcessosSincronizados(processos);
        System.exit(0);
    }
}
