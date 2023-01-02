package BickRace;

import java.util.Arrays;
import java.util.Comparator;

/**
 * klasse für die Rennen
 */
public class Race {

    /**
     * Konstanten für ein Rennen
     */
    public static final int numberOfBikes = 5;
    public static final int numberOfRounds = 10;

    /**
     * Methode um die Zeit zu vergleichen, die ein Bike braucht
     */
    public static final Comparator<Bike> zeitComparator =
            Comparator.comparing(Bike::getTimePassed);

    /**
     * Bikes Erzeugen
     * @param i
     * @return
     */
    public static Bike[] createBikes(int i) {
        Bike[] bikes = new Bike[i];
        for (int j = 0; j < bikes.length; j++) {
            bikes[j] = new Bike("Bike " + (j + 1), numberOfRounds);
        }
        return bikes;
    }

    /**
     * Ein Ergebniss Ausgeben für jedes Bike in Sortierter rihenfolge
     * @param bikes
     */
    public static void raceResult(Bike[] bikes) {
        Arrays.sort(bikes, zeitComparator);
        int placement = 1;
        for (Bike bike : bikes) {
            System.out.println(placement + ". position: " +
                    bike.getNameOfBike() + " Time: " + bike.getTimePassed());
            placement++;
        }
    }

    /**
     * Methode um ein Rennen zu8 satrten (ohne Umfälle)
     * @param anyBike
     * @throws InterruptedException
     */
    public static void startRace(Bike[] anyBike) throws InterruptedException {
        for (Bike bike : anyBike) {
            bike.start();
        }

        for (Bike bike : anyBike) {
            bike.join();
        }

        raceResult(anyBike);
    }

    /**
     * Methode um ein Rennen zu starten (mit umfälle)
     * @param anyBike
     * @throws InterruptedException
     */
    public static void startRaceWhithAccidents(Bike[] anyBike) throws InterruptedException {

        for (Bike bike : anyBike) {
            bike.start();
        }

        Accident accident = new Accident(anyBike);
        accident.start();

        for (Bike bikes : anyBike) {
            bikes.join();
        }

        if (accident.accidentHappend()) {
            accidentResults(anyBike);
            System.err.println("Rennen musste aufgrund eines Sturzes abgebrochen werden!");
        } else
            raceResult(anyBike);
    }

    public static void accidentResults(Bike[] bikes) {
        Arrays.sort(bikes, zeitComparator);
        for (Bike bike : bikes) {
            System.out.println(bike.getNameOfBike() + " Runden Gefahren: " + bike.getRoundsDriven() + " " + " Time: " + bike.getTimePassed());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Bike[] bikes = createBikes(numberOfBikes);
//        startRace(bikes);
        startRaceWhithAccidents(bikes);
    }
}
