package AufgabeStein;


import java.util.Random;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        Player[] player = new Player[2];
        Judge judge = new Judge(player);
        for (int j = 0; j < player.length; j++) {
            player[j] = new Player(judge,j);
        }

        for (Player people : player) {
            people.start();
        }

        judge.start();
        judge.join();
    }

}
