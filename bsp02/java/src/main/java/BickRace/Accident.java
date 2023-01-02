package BickRace;

public class Accident extends Thread {

    /**
     * Konstante für einen Umfall
     */
    public boolean accident = false;
    public Bike[] bikeArr;

    /**
     * Abfrage, ob ein Umfall Pasiert ist.
     * @return accident
     */
    public boolean accidentHappend() {
        return accident;
    }

    /**
     * Konstruktor für Accident
     * @param bikes
     */
    public Accident(Bike[] bikes){
        this.bikeArr = bikes;
    }

    /**
     * setzt accident auf true
     */
    public void crashed() {
        accident = true;
    }

    /**
     * Rum methode für ein accident
     * liefert ein accident wieder, wenn das rennen langsemer war als die zeit bis zum Umfall
     */
    @Override
    public void run() {
        int i = 0;
        while (!this.accident && i < Race.numberOfRounds) {
            int rand = (int) (Math.random() * 100 + 1);
            try {
                sleep(rand);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (rand <= 10) {
                crashed();
            }
            i++;
        }
        for (int j = 0; j < bikeArr.length; j++){
                this.bikeArr[j].interrupt();
        }
    }
}

