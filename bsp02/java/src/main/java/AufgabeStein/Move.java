package AufgabeStein;

public enum Move {
        ROCK ("rock"),
        PAPER ("paper"),
        SCISSORS("scissors");

        Move(String shapeName){
            this.shape = shapeName;
        }

        private String shape;

        public String getShape() {
            return shape;
        }
}
