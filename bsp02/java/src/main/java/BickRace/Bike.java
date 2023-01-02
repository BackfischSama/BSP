package BickRace;

/**
 * Classe für die Einzelenen Bikes
 */
public class Bike extends Thread {

    /**
     * Konstanten für die Bikes
     */
    private final String nameOfBike;
    private final int numberOfRounds;
    private int roundsDriven = 0;
    private long timePassed = 0;

    public int getRoundsDriven() {
        return roundsDriven;
    }

    /**
     * Konstruktor für Bikes
     * @param nameOfBike
     * @param numberOFrounds
     */
    public Bike(String nameOfBike, int numberOFrounds) {
        this.nameOfBike = nameOfBike;
        this.numberOfRounds = numberOFrounds;
    }

    public String getNameOfBike() {
        return nameOfBike;
    }

    public long getTimePassed() {
        return this.timePassed;
    }

    /**
     * Run methode eines Einzelnen Bikes
     * Ausgabe die Nummer des Bikes und die Dauer des Rennen
     */
    @Override
    public void run() {
        while (roundsDriven < numberOfRounds && !this.isInterrupted()) {
            long rundenZeit = Math.round(Math.random() * 100);
            try {
                sleep(rundenZeit);
            } catch (InterruptedException e) {
                break;
            }
            this.timePassed += rundenZeit;
            this.roundsDriven++;
        }
        if(this.isInterrupted()){
            System.err.println("Umfall ist passiert");
        }
    }
}

