package semaforo;

import java.time.LocalTime;

public class Cruzamento {
    public static void main(String[] args) {
                       
        Semaforo semaforoA = new Semaforo(Semaforo.Cores.VERDE, 5000, 2000, 3000);
        Semaforo semaforoB = new Semaforo(Semaforo.Cores.VERMELHO, 5000, 2000, 3000);
        
        Thread tarefaSemaforoA = new Thread(semaforoA, "SEMÁFORO A");
        Thread tarefaSemaforoB = new Thread(semaforoB, "SEMÁFORO B");
        tarefaSemaforoA.start();
        tarefaSemaforoB.start();
    }
}

class Semaforo implements Runnable {
    
    Cores estadoAtual;
    int tempoVerde, tempoAmarelo, tempoVermelho;
    
    public static enum Cores {
        VERDE(1), 
        AMARELO(2), 
        VERMELHO(3);
        private int valorDaCor;
        
        Cores(int valorDaCor) {
            this.valorDaCor = valorDaCor;
        }
        
        public int getValorDaCor(){
            return this.valorDaCor;
        }
    }
    
    public Semaforo (Cores cor, int tempoVerde, int tempoAmarelo, int tempoVermelho) {
        estadoAtual = cor;
        this.tempoVerde = tempoVerde;
        this.tempoAmarelo = tempoAmarelo;
        this.tempoVermelho = tempoVermelho;
    }

    @Override
    public void run() {
        Thread threadAtual = Thread.currentThread();
        
        System.out.println("Início da thread " + threadAtual.getName() + ": " +
                           LocalTime.now());
        
        
        for(int mudancas = 0; mudancas < 5; mudancas++) {
                if(threadAtual.getName().equals("SEMÁFORO B")) {
                    System.out.print("\t\t\t");
                }
            System.out.println(threadAtual.getName() + ":" + this.estadoAtual + 
                               " às " + LocalTime.now());
            try { 
                if (this.estadoAtual.getValorDaCor() == Cores.VERDE.getValorDaCor()) {
                    Thread.sleep(this.tempoVerde);
                    this.estadoAtual = Cores.AMARELO;
                }   
                else if (this.estadoAtual.getValorDaCor() == Cores.AMARELO.getValorDaCor()) {
                    Thread.sleep(this.tempoAmarelo);
                    this.estadoAtual = Cores.VERMELHO;
                }               
                else if (this.estadoAtual.getValorDaCor() == Cores.VERMELHO.getValorDaCor()) {
                    Thread.sleep(this.tempoVermelho);
                    this.estadoAtual = Cores.VERDE;
                }            
            } catch (InterruptedException ex) { }
        }
    }
}
