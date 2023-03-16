package sincronizacao;

/**
 * @author José Vinícius de Carvalho Oliveira
 */

public class Processo {
    private int id;
    private int tempo;

    public Processo(int id, int tempo) {
        this.id = id;
        this.tempo = tempo;
    }

    public int getId() {
        return id;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
