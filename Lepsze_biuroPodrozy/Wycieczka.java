package Lepsze_biuroPodrozy;

// Klasa reprezentująca wycieczkę
public class Wycieczka {
    private String opisPodrozy; // Opis wycieczki
    private double cena; // Cena wycieczki
    private int iloscMiejsc; // Ilość dostępnych miejsc

    // Konstruktor klasy Wycieczka
    public Wycieczka(String opisPodrozy, double cena, int iloscMiejsc) {
        this.opisPodrozy = opisPodrozy;
        this.cena = cena;
        this.iloscMiejsc = iloscMiejsc;
    }

    // Metoda zwracająca cenę wycieczki
    public double getCena() {
        return cena;
    }

    // Metoda zwracająca opis wycieczki
    public String getOpisPodrozy() {
        return opisPodrozy;
    }

    // Metoda zwracająca ilość miejsc
    public int getIloscMiejsc() {
        return iloscMiejsc;
    }

    // Metoda toString do wyświetlania informacji o wycieczce w czytelnej formie
    @Override
    public String toString() {
        return "Wycieczka: " + opisPodrozy + ", Cena: " + String.format("%.2f", cena) + " PLN, Miejsca: " + iloscMiejsc;
    }
}