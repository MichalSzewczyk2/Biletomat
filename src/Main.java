public class Main {

    public static void main(String[] args) throws MyException {

        Karta debet = new Karta(40);
        Gotowka gotowka = new Gotowka(2, 0, 5, 0, 0,0);
        Gotowka wBiletomacie = new Gotowka(5,5,5,5,5,5);

        String[] rBiletow = new String[2];
        rBiletow[0] = "Normalny";
        rBiletow[1] = "Ulgowy";

        Double[] cBiletow = new  Double[2];
        cBiletow[0] = 2.4;
        cBiletow[1] = 1.6;

        int[] pBilety = new int[2];
        pBilety[0] = 10;
        pBilety[1] = 10;



        Biletomat kasa = new Biletomat("11-11-2022", "Krakow", rBiletow, cBiletow, pBilety,2,wBiletomacie);
        try{
            Bilet[] kupione = kasa.kupBilet(debet);
        }catch (MyException e){
            throw e;
        }


        System.out.println(gotowka.getKwota());
        System.out.println(kasa.toString());

        try{
            kasa.wydrokujTransakcje("10-11-2022");
        }catch (MyException e){
            throw e;
        }


    }
}


