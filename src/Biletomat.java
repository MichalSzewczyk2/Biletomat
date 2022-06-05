import java.util.Scanner;

public class Biletomat implements IBiletomat{

    //Dane o biletomacie
    String data;
    String lokalizacja;

    //Zapis czynnosci biletomatu
    Transakcja[] transakcje;
    int iloscTransakcji;

    int[] pozostaleBilety;

    //Informacje o biletach
    String[] rodzajeBiletow;
    Double[] cenyBiletow;
    int iloscRodzajow;

    Gotowka gotowka;

    public Biletomat(String data, String lokalizacja, String[] rodzajeBiletow, Double[] cenyBiletow, int[] pozostaleBilety, int iloscRodzajow, Gotowka gotowka) {
        this.data = data;
        this.lokalizacja = lokalizacja;

        this.transakcje = null;
        this.iloscTransakcji = 0;

        this.pozostaleBilety = pozostaleBilety;

        this.rodzajeBiletow = rodzajeBiletow;
        this.cenyBiletow = cenyBiletow;
        this.iloscRodzajow = iloscRodzajow;

        this.gotowka = gotowka;

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int[] getPozostaleBilety() {
        return pozostaleBilety;
    }

    public void setPozostaleBilety(int[] pozostaleBilety) {
        this.pozostaleBilety = pozostaleBilety;
    }

    public String[] getRodzajeBiletow() {
        return rodzajeBiletow;
    }

    public void setRodzajeBiletow(String[] rodzajeBiletow) {
        this.rodzajeBiletow = rodzajeBiletow;
    }

    public Double[] getCenyBiletow() {
        return cenyBiletow;
    }

    public void setCenyBiletow(Double[] cenyBiletow) {
        this.cenyBiletow = cenyBiletow;
    }

    public int getIloscRodzajow() {
        return iloscRodzajow;
    }

    public void setIloscRodzajow(int iloscRodzajow) {
        this.iloscRodzajow = iloscRodzajow;
    }

    public Gotowka getGotowka() {
        return gotowka;
    }

    public void setGotowka(Gotowka gotowka) {
        this.gotowka = gotowka;
    }

    public String toString(){

        int[] sumyBiletow = new int[iloscRodzajow];


        for(int i = 0; i<iloscTransakcji; i++){
            for(int n = 0; n<iloscRodzajow; n++){
                sumyBiletow[n] += transakcje[i].ile[n];
            }
        }
        StringBuilder sB;
        sB = new StringBuilder();
        for(int i = 0; i < iloscRodzajow; i++){
            if(sumyBiletow[i] != 0){
                sB.append(data);
                sB.append(": ");
                sB.append(rodzajeBiletow[i]);
                sB.append(": ");
                sB.append(sumyBiletow[i]);
                sB.append(": ");
                sB.append(Math.round(sumyBiletow[i]*cenyBiletow[i] * 100.0) / 100.0);
                sB.append("\n");
            }
        }
        return sB.toString();
    }

    public void wydrokujTransakcje(String date) throws MyException {
        int count = 1;

        for(int i = 0; i<iloscTransakcji; i++) {

            if (date.equals(transakcje[i].data)) {
                for (int n = 0; n < iloscRodzajow; n++) {
                    if(transakcje[i].ile[n]!=0){
                        System.out.println(count+". "+rodzajeBiletow[n]+": "+transakcje[i].ile[n]);
                        count++;
                    }
                }
            }
            else {
                throw new MyException("Nie ma takiej daty");
            }
        }
    }

    public Biletomat rozpocznijSerwis(){
        return this;
    }

    public Transakcja operacja() throws MyException {

        boolean flow = true;
        Transakcja zamowienie = null;
        int[] ile = new int[iloscRodzajow];

        while (flow) {
            System.out.println("Jaki bilet chcesz kupic?");
            for (int i = 0; i < iloscRodzajow; i++) {
                System.out.println(i + ". " + rodzajeBiletow[i]);
            }
            System.out.println((iloscRodzajow + 1) + ". Zakoncz");

            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();

            if (n == iloscRodzajow + 1) {
                flow = false;
                return null;
            } else if ((n > iloscRodzajow + 1) && (n <= 0)) {
                throw new MyException("Nieprawidłowe dane");
            } else {
                System.out.println("Ile biletow chcesz kupic?");
                ile[n] = scan.nextInt();
                if (ile[n] <= 0) {
                    throw new MyException("Nieprawidłowe dane");

                } else if (pozostaleBilety[n] < ile[n]) {
                    throw new MyException("Nie ma wystarczająco biletów");

                } else {
                    System.out.println("Dodano " + ile[n] + " biletow " + rodzajeBiletow[n] + " do zamowienia");
                    System.out.println("1. Kontynuj zakupy");
                    System.out.println("2. Zakoncz i zaplac");
                    int wybor = scan.nextInt();
                    if (wybor == 1){
                        System.out.println("Kontynuujemy zakupy");
                    }
                    else if (wybor == 2) {
                        Transakcja nowa = new Transakcja(data, ile);

                        iloscTransakcji++;
                        Transakcja[] tr = new Transakcja[iloscTransakcji];
                        if (iloscTransakcji - 1 > 0) System.arraycopy(transakcje, 0, tr, 0, iloscTransakcji - 1);
                        tr[iloscTransakcji - 1] = nowa;
                        transakcje = tr;
                        zamowienie = nowa;
                        flow = false;
                    }
                    else {
                        throw new MyException("Nieprawidłowe dane");
                    }
                }
            }
        }
        return zamowienie;
    }

    public Gotowka wydajReszte(double kwota){
        Gotowka reszta = new Gotowka();
        while(kwota <= 0.9){
            if(gotowka.moneta5 > 0){
                if(kwota >= 5){
                    reszta.dodajPieniadze(1,0,0,0,0,0);
                    kwota -= 5;
                    gotowka.moneta5--;
                }
                if(gotowka.moneta2 > 0)

                    if( kwota >= 2 ){
                        reszta.dodajPieniadze(0,1,0,0,0,0);
                        kwota -= 2;
                        gotowka.moneta2--;
                    }
                    if( gotowka.moneta1 > 0 ) {
                        if(kwota >= 1){
                            reszta.dodajPieniadze(0,0,1,0,0,0);
                            kwota -= 1;
                            gotowka.moneta1--;
                        }
                        if( gotowka.moneta05 > 0) {
                            if(kwota >= 0.5){
                                reszta.dodajPieniadze(0,0,0,1,0,0);
                                kwota -= 0.5;
                                gotowka.moneta05--;
                            }
                            if( gotowka.moneta02 > 0) {
                                if(kwota >= 0.2){
                                    reszta.dodajPieniadze(0,0,0,0,1,0);
                                    kwota -= 0.5;
                                    gotowka.moneta02--;
                                }
                                if(gotowka.moneta01 >1) {
                                    if(kwota >= 0.1){
                                        reszta.dodajPieniadze(0,0,0,0,0,1);
                                        kwota -= 0.5;
                                        gotowka.moneta01--;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        return reszta;
    }

    public Bilet[] kupBilet(Gotowka zaplata) throws MyException {

        Transakcja zamowienie = operacja();

        double kwota = 0.0;
        int ileBiletow = 0;
        for (int i = 0; i < iloscRodzajow; i++) {
            ileBiletow += zamowienie.ile[i];
            kwota += zamowienie.ile[i]*cenyBiletow[i];
        }

        Bilet[] bilety = new Bilet[ileBiletow];

        if(kwota < zaplata.getKwota() && kwota < gotowka.getKwota()){
            zaplata = wydajReszte(zaplata.getKwota()-kwota);

            int count = 0;
            for(int i = 0; i < iloscRodzajow; i++){
                for(int n = 0; n < zamowienie.ile[i]; n++){
                    Bilet tmp = new Bilet(rodzajeBiletow[i], cenyBiletow[i]);
                    bilety[count] = tmp;
                    count++;
                }
            }
            return bilety;
        }
        else {
            throw new MyException("Nie mozna zrealizowac platnosci");
        }
    }

    public Bilet[] kupBilet(Karta karta) throws MyException {

        Transakcja zamowienie = operacja();

        double kwota = 0.0;
        int ileBiletow = 0;
        for (int i = 0; i < iloscRodzajow; i++) {
            ileBiletow += zamowienie.ile[i];
            kwota += zamowienie.ile[i]*cenyBiletow[i];
        }

        Bilet[] bilety = new Bilet[ileBiletow];
        if(kwota < karta.getKwota()){
            karta.zaplac(kwota);
            int count = 0;
            for(int i = 0; i < iloscRodzajow; i++){
                for(int n = 0; n < zamowienie.ile[i]; n++){
                    Bilet tmp = new Bilet(rodzajeBiletow[i], cenyBiletow[i]);
                    bilety[count] = tmp;
                    count++;
                }
            }
            return bilety;
        }
        else{
            throw new MyException("Nie ma wystarczająco środkow");
        }
    }
}
