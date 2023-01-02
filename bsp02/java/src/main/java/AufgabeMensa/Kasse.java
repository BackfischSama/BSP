package AufgabeMensa;

import java.util.Queue;
import java.util.concurrent.*;

public class Kasse extends Thread{

    private int id;
    public int verkaeufe = 0;
    private static final int anzahlKunden = 1;
    private Semaphore plaetze;
    public boolean offen = false;
    public int warteschlange = 0;
    public Queue<Kunde> kunden;

    public Kasse( int id){
        this.id = id;
        plaetze = new Semaphore(anzahlKunden);
    }

    public void anKasseStellen() throws InterruptedException {
        plaetze.acquire();
        try {
            verkaufe();
        }
        finally {
            plaetze.release();
        }
    }

    public void verkaufe() throws InterruptedException {
        int schlafZeit = (int) (500 * Math.random());
        Thread.sleep(schlafZeit);
    }


    private void anzahlVerkaufe(){
        System.out.println("Kasse " + id + " hat " + verkaeufe + " mal etwas verkauft");
    }

    @Override
    public synchronized void run(){
        while(!interrupted()){
            if(warteschlange == 0){
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (warteschlange > 0){
                offen = true;
                kunden.poll().notifyAll();
                offen = false;
            }
            if(verkaeufe >10){
                for(int i = 0; i < kunden.size(); i++){
                    kunden.poll().interrupt();
                }

            }
        }
        anzahlVerkaufe();
    }
}
