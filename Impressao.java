package armadilhas;

import static armadilhas.Impressao.QTDEIMPRESSORAS;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Impressao {

    private static final int QTDETHREADS = 10;
    public static final int QTDEIMPRESSORAS = 3;
    public static int contador = 1;

    public static void main (String args[]){

        FilaDeImpressao filaDeImpressao = new FilaDeImpressao();

        ArrayList<Tarefas> tarefa = new ArrayList<>();
        ArrayList<Thread> thread = new ArrayList<>();

        for (int i = 0; i < QTDETHREADS; i++) {
            tarefa.add(new Tarefas(filaDeImpressao));
            thread.add(new Thread(tarefa.get(i), "Thread " + i));
            thread.get(i).start();
        }
    }
}

class Tarefas implements Runnable {

    private FilaDeImpressao filaDeImpressao;

    public Tarefas(FilaDeImpressao filaDeImpressao) {
        this.filaDeImpressao = filaDeImpressao;
    }

    @Override
    public void run() {
        System.out.printf("%s vai imprimir um novo documento...\n",
                          Thread.currentThread().getName());
        filaDeImpressao.imprimir(new Object());
    }
}

class FilaDeImpressao {

    private final Semaphore controlador;
    private final boolean impressorasDisponiveis[];

    public FilaDeImpressao() {
        controlador = new Semaphore(QTDEIMPRESSORAS);
        impressorasDisponiveis = new boolean[QTDEIMPRESSORAS];
        for (int i=0; i<QTDEIMPRESSORAS; i++){
            impressorasDisponiveis[i] = true;
        }
    }

    public void imprimir (Object documento) {
        try {
            controlador.acquire();
            int impressora = getImpressora();

            Long tempoImprimindo = (long)(Math.random()*10);
            System.out.printf("%s -> %s imprimindo na impressora %d; "
                              + "levará %d segundos.\n", LocalTime.now(),
                              Thread.currentThread().getName(),impressora,
                              tempoImprimindo);
            TimeUnit.SECONDS.sleep(tempoImprimindo);

            impressorasDisponiveis[impressora] = true;
        } catch (InterruptedException e) { }
        finally {
            controlador.release();
        }
    }

    private int getImpressora() {
        int disponiveis = -1;
        try {
            for (int i = 0; i<impressorasDisponiveis.length; i++) {
                if (impressorasDisponiveis[i]){
                    disponiveis = i;
                    impressorasDisponiveis[i]=false;
                    break;
                }
            }
        } catch (Exception e) { }
        return disponiveis;
    }
}
