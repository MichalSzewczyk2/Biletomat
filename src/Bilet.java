public class Bilet{

    private String rodzajBiletu;
    private Double cena;

    public Bilet(String rodzajBiletu, Double cena) {
        this.rodzajBiletu = rodzajBiletu;
        this.cena = cena;
    }

    public String getRodzajBiletu() {
        return rodzajBiletu;
    }

    public void setRodzajBiletu(String rodzajBiletu) {
        this.rodzajBiletu = rodzajBiletu;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String toString(){
        return rodzajBiletu + ": " + cena;
    }
}
