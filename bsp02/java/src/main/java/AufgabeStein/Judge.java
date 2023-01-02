package AufgabeStein;

import java.util.concurrent.*;

public class Judge extends Thread {

    private int gamesPlayed;
    private volatile boolean threadFinished = false;
    private final Player[] playerarr;
    private int maxRunden = 10;
    public boolean warten = false;
    public Player player;

    public Judge(Player[] playerarr) {
        this.playerarr = playerarr;
    }

    public void terminate() {
        threadFinished = true;
        System.out.println("Number of games played: " + gamesPlayed);
    }

    public synchronized void jugde() {
        gamesPlayed++;
        if (playerarr[0].getShape.equals(playerarr[1].getShape)) {
            for (Player player : playerarr) {
                player.draw++;
            }
        } else if (playerarr[0].getShape.equals("rock")) {
            if (playerarr[1].getShape.equals("paper")) {
                playerarr[1].win++;
                playerarr[0].lose++;
            } else if (playerarr[1].getShape.equals("scissors")) {
                playerarr[0].win++;
                playerarr[1].lose++;
            }
        } else if (playerarr[0].getShape.equals("paper")) {
            if (playerarr[1].getShape.equals("scissors")) {
                playerarr[1].win++;
                playerarr[0].lose++;
            } else if (playerarr[1].getShape.equals("rock")) {
                playerarr[0].win++;
                playerarr[1].lose++;
            } else if (playerarr[0].getShape.equals("scissors")) {
                if (playerarr[1].getShape.equals("rock")) {
                    playerarr[1].win++;
                    playerarr[0].lose++;
                } else if (playerarr[1].getShape.equals("paper")) {
                    playerarr[0].win++;
                    playerarr[1].lose++;
                }
            }

        }
    }

    @Override
    public synchronized void run(){
        while(!interrupted()) {
                if (playerarr[0].stillWait) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                warten = true;
                jugde();
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (gamesPlayed == maxRunden) {
                    terminate();
                }
                if (threadFinished) {
                    for (Player player : playerarr) {
                        player.interrupt();
                    }
                }
                notifyAll();
            }
    }
}
