package ibf2024.assessment.paf.batch4.models;

public class BeerSummary {

        private int beerId;
        private String beerName;
        private String beerDescription;
        public int getBeerId() {
            return beerId;
        }
        public BeerSummary() {
        }
        public BeerSummary(int beerId, String beerName, String beerDescription) {
            this.beerId = beerId;
            this.beerName = beerName;
            this.beerDescription = beerDescription;
        }
        public void setBeerId(int beerId) {
            this.beerId = beerId;
        }
        public String getBeerName() {
            return beerName;
        }
        public void setBeerName(String beerName) {
            this.beerName = beerName;
        }
        public String getBeerDescription() {
            return beerDescription;
        }
        public void setBeerDescription(String beerDescription) {
            this.beerDescription = beerDescription;
        }
    
}
