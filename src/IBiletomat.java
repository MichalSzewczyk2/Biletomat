public interface IBiletomat {

    public default Bilet[] kupBilet (){
        System.out.println("Nie można kupic biletu!");
        return null;
    }
    public default Transakcja operacja() throws MyException {
        System.out.println("Nie mozna przeprowadzic operacji");
        return null;
    }
}
