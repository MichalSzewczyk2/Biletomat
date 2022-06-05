public class Gotowka extends Pieniadz{

    int moneta5;
    int moneta2;
    int moneta1;
    int moneta05;
    int moneta02;
    int moneta01;

    public Gotowka(){
        this.moneta5 = 0;
        this.moneta2 = 0;
        this.moneta1 = 0;
        this.moneta05 = 0;
        this.moneta02 = 0;
        this.moneta01 = 0;
    }

    public Gotowka(int moneta5, int moneta2, int moneta1, int moneta05, int moneta02, int moneta01) {
        this.moneta5 = moneta5;
        this.moneta2 = moneta2;
        this.moneta1 = moneta1;
        this.moneta05 = moneta05;
        this.moneta02 = moneta02;
        this.moneta01 = moneta01;
        setKwota(moneta5*5 + moneta2*2 + moneta1 + moneta05*0.5 + moneta02*0.2 + moneta01*0.1);
    }

    public void zaplac(int moneta5, int moneta2, int moneta1, int moneta05, int moneta02, int moneta01){
        this.moneta5 -= moneta5;
        this.moneta2 -= moneta2;
        this.moneta1 -= moneta1;
        this.moneta05 -= moneta05;
        this.moneta02 -= moneta02;
        this.moneta01 -= moneta01;
        setKwota(this.moneta5*5 + this.moneta2*2 + this.moneta1 + this.moneta05*0.5 + this.moneta02*0.2 + this.moneta01*0.1);
    }
    public void dodajPieniadze(int moneta5, int moneta2, int moneta1, int moneta05, int moneta02, int moneta01){
        this.moneta5 += moneta5;
        this.moneta2 += moneta2;
        this.moneta1 += moneta1;
        this.moneta05 += moneta05;
        this.moneta02 += moneta02;
        this.moneta01 += moneta01;
        setKwota(this.moneta5*5 + this.moneta2*2 + this.moneta1 + this.moneta05*0.5 + this.moneta02*0.2 + this.moneta01*0.1);
    }
}
