package sincronizacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author José Vinícius de Carvalho Oliveira
 */

public class Servidor implements Runnable {
    private ServerSocket serverSocket;
    private List<Processo> processos;

    public Servidor(List<Processo> processos) throws IOException {
        this.serverSocket = new ServerSocket(Constantes.PORTA);
        this.processos = processos;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                int tempoAtual = (int) input.readObject();
                List<Integer> tempos = new ArrayList<>();
               
                for (Processo processo : processos) {
                    if (processo.getId() != 0) {
                        tempos.add(processo.getTempo());
                    }
                }
                
                int tempoMedio = SincronizarRelogiosHelper.calcularTempoMedio(tempoAtual, tempos);
                SincronizarRelogiosHelper.atualizarTempos(processos, tempoMedio);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}