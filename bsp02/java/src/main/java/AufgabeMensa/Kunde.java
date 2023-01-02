package AufgabeMensa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Kunde extends Thread{

    private List<Kasse> kassenListe;
    private int max = 1000;
    private int min = 100;
    private int randomNumber = min + (int)(Math.random() * ((max - min)+1));
    public int id;

    public Kunde(int id,List<Kasse> kassenListe){
        this.id = id;
        this.kassenListe = kassenListe;
    }


    public Kasse istKasseAuf(List<Kasse> kassenListe){
        for (Kasse kasse: kassenListe) {
            if(kasse.offen){
                return kasse;
            }
        }
        return null;
    }

    public void bezahlen(Kasse kasse){
        kasse.verkaeufe++;
    }

    @Override
    public synchronized void run(){
        while(!interrupted()) {
            if (istKasseAuf(kassenListe) != null) {
            bezahlen(istKasseAuf(kassenListe));
                System.out.println("ich habe etwas Gekauft.");
                try {
                    sleep(randomNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    sleep(randomNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else{
                for (Kasse kasse : kassenListe) {
                    int minQueue = kassenListe.get(0).warteschlange;
                    if (minQueue >= kasse.warteschlange) {
//                        Kasse.kunden.add();
                    }
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
