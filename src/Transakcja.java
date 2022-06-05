public class Transakcja {

    String data;
    int[] ile;

    public Transakcja(String data, int[] ile) {
        this.data = data;
        this.ile= ile;
    }
    public Transakcja() {
        this.data = "";
        this.ile= null;
    }
}
