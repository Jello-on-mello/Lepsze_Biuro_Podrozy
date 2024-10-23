package Lepsze_biuroPodrozy;

public class Klient {
    private String imie;
    private String nazwisko;
    private int wiek;
    private String adres;

    public Klient(String imie, String nazwisko, int wiek, String adres) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.adres = adres;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko + ", wiek: " + wiek + ", adres: " + adres;
    }
}
