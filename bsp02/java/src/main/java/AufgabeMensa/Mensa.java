package AufgabeMensa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Mensa {

    private static final int maxKunden = 10;
    private static final int maxKassen = 1;

    private static Kasse[] kassenStart(int i){
        Kasse[] kassenarr = new Kasse[i];
            for(int j = 0; j < i; j++){
                kassenarr[j] = new Kasse(j);
            }
            return kassenarr;
    }

    private static Kunde[] kundenStart(int i, List<Kasse> kasse){
        Kunde[] kundenarr = new Kunde[i];
        for(int j = 0; j < i; j++){
            kundenarr[j] = new Kunde(j, kasse);
        }
        return kundenarr;
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(1);
            Kasse[] kassenarr = new Kasse[maxKassen];
            for (int j = 0; j < maxKassen; j++) {
                kassenarr[j] = new Kasse(j);
            }
        ArrayList<Kasse> kassenlist = new ArrayList<>(Arrays.asList(kassenarr).subList(0, maxKassen));
        Kunde[] kundenarr = new Kunde[maxKunden];
        for(int j = 0; j < maxKunden; j++){
            kundenarr[j] = new Kunde(j, kassenlist);
        }

        for (Kasse kassee: kassenarr) {
            kassee.start();
        }
        for (Kunde kunde: kundenarr) {
            kunde.start();
        }
        try {
            Thread.sleep(5000);
        } catch ( InterruptedException e){
            e.printStackTrace();
        }

        for(Kunde kunde: kundenarr){
            kunde.interrupt();
            kunde.join();
        }
    }
}
