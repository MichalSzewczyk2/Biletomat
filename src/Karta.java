public class Karta extends Pieniadz{

    public Karta(double kwota) {
        setKwota(kwota);
    }

    public void zaplac(Double kwota){
        setKwota(getKwota()-kwota);
    }
}
