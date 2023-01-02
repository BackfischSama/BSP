package AufgabeStein;

import java.util.Random;
import java.util.concurrent.*;

public class Player extends Thread{

    public int win, draw, lose;
    private int playerId;
    public String getShape;
    private Judge judge;
    private String shape;
    public boolean stillWait = true;

    public int getPlayerId() {
        return playerId;
    }

    public String getShape() {
        return shape;
    }

    public Player (Judge judge, int playerId){
        this.judge = judge;
        this.playerId = playerId;
    }

    private String getMove() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        getShape = Move.values()[randomNumber].getShape();
        System.out.println(getShape);
        return getShape;
    }

    public void result(){
        System.out.println("player: "+ playerId +" Wins: " + win + " Loses: " + lose + " Draws: " + draw);
    }

    @Override
    public synchronized void run(){
        while(!interrupted()) {
            getMove();
            stillWait = false;
            notifyAll();
            if(interrupted()){
                break;
            }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stillWait = true;
        }
        result();
    }
}
