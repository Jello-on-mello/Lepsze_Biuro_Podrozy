package Lepsze_biuroPodrozy;

// Klasa reprezentująca klienta
public class Klient {
    private String imie; // Imię klienta
    private String nazwisko; // Nazwisko klienta
    private int wiek; // Wiek klienta
    private String adres; // Adres klienta

    // Konstruktor klasy Klient
    public Klient(String imie, String nazwisko, int wiek, String adres) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.adres = adres;
    }

    // Metoda zwracająca imię klienta
    public String getImie() {
        return imie;
    }

    // Metoda zwracająca nazwisko klienta
    public String getNazwisko() {
        return nazwisko;
    }

    // Metoda toString do wyświetlania danych klienta w czytelnej formie
    @Override
    public String toString() {
        return imie + " " + nazwisko + ", wiek: " + wiek + ", adres: " + adres;
    }
}