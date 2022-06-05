public class Serwis extends Biletomat{

    String[] czynnosciSerwisowe;
    int licznikCzynnosci;

    public Serwis(String data, String lokalizacja, String[] rodzajeBiletow, Double[] cenyBiletow, int[] pozostaleBilety, int iloscRodzajow, Gotowka gotowka) {
        super(data, lokalizacja, rodzajeBiletow, cenyBiletow, pozostaleBilety, iloscRodzajow, gotowka);
        czynnosciSerwisowe = new String[100];
        licznikCzynnosci = 0;
    }

    @Override
    public Bilet[] kupBilet(Karta karta) {
        System.out.println("Operacja niedostepna podzcas serwisu");
        return null;
    }

    @Override
    public Bilet[] kupBilet(Gotowka zaplata) {
        System.out.println("Operacja niedostepna podzcas serwisu");
        return null;
    }

    public void ZmienBilety(String[] noweBilety, Double[] noweCeny){
        setIloscRodzajow(noweBilety.length);
        setRodzajeBiletow(noweBilety);
        setCenyBiletow(noweCeny);

        czynnosciSerwisowe[licznikCzynnosci] = "Zmiana biletow";
        licznikCzynnosci++;
    }

    public void ustawDate(String nowaData){
        setData(nowaData);
        czynnosciSerwisowe[licznikCzynnosci] = "Zmiana daty";
        licznikCzynnosci++;
    }

    public void wypiszBlad(){
        int[] pB = getPozostaleBilety();
        for (int j : pB) {
            if (j == 0) {
                System.out.println("Brak blankietow papierowych!");
            }
        }
        Gotowka gt = getGotowka();
        if(gt.getKwota() < 10 && gt.getKwota() != 0){
            System.out.println("Malo monet w biletomacie");
        }
        if(gt.getKwota() == 0){
            System.out.println("Brak gotowki w biletomacie");
        }
        czynnosciSerwisowe[licznikCzynnosci] = "Wpisanie bledow";
        licznikCzynnosci++;
    }

    public void wypiszCzynnosci(){

        for(int i = 0; i < licznikCzynnosci+1; i++){
            System.out.println(czynnosciSerwisowe[i]);
        }
    }

    public Biletomat zakonczSerwis(){
        return this;
    }
}