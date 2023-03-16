package sincronizacao;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author José Vinícius de Carvalho Oliveira
 */

public class Cliente implements Runnable {
    private Processo processo;
    private ServidorTempo servidorTempo;

    public Cliente(Processo processo, ServidorTempo servidorTempo) {
        this.processo = processo;
        this.servidorTempo = servidorTempo;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(Constantes.HOST, Constantes.PORTA)) {
            socket.setSoTimeout(Constantes.TIMEOUT);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(servidorTempo.getTempoAtual());
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
