public class Informacii {

    private String koj_otklucil;
    private String tip_korisnik;
    private String prostorijaKojVlegol;
    private String tip_prostorija;

    public Informacii(){

    }

    public Informacii(String koj_otklucil, String tip_korisnik, String prostorijaKojVlegol, String tip_prostorija) {
        this.koj_otklucil = koj_otklucil;
        this.tip_korisnik = tip_korisnik;
        this.prostorijaKojVlegol = prostorijaKojVlegol;
        this.tip_prostorija = tip_prostorija;
    }

    @Override
    public String toString() {
        return
                "koj_otklucil=" + koj_otklucil + ":" +
                "tip_korisnik=" + tip_korisnik + ":" +
                "prostorijaKojVlegol=" + prostorijaKojVlegol + ":" +
                "tip_prostorija=" + tip_prostorija;
    }
}
