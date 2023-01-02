package AufgabeStein;

public enum Result {
        WIN("win"),
        LOSE("lose"),
        DRAW("draw");

        Result (String resultName){
                this.outcome = resultName;
        }

        public String outcome;

        public String getOutcome() {
            return outcome;
        }
}
