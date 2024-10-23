package Lepsze_biuroPodrozy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Klasa reprezentująca wykupioną wycieczkę
public class WykupionaWycieczka {
    private Klient klient; // Klient wykupujący wycieczkę
    private Wycieczka wycieczka; // Szczegóły wykupionej wycieczki
    private Data data; // Daty wyjazdu i powrotu
    private double cenaZRabatem; // Cena po rabacie

    // Konstruktor klasy WykupionaWycieczka
    public WykupionaWycieczka(Klient klient, Wycieczka wycieczka, Data data) {
        this.klient = klient;
        this.wycieczka = wycieczka;
        this.data = data;
        this.cenaZRabatem = wycieczka.getCena() * 0.9; // Początkowy rabat 10%
    }

    // Metoda zwracająca klienta
    public Klient getKlient() {
        return klient;
    }

    // Metoda zwracająca wykupioną wycieczkę
    public Wycieczka getWycieczka() {
        return wycieczka;
    }

    // Metoda zwracająca daty wycieczki
    public Data getData() {
        return data;
    }

    // Sprawdza, czy dwie wycieczki są w odstępie 30 dni
    public boolean czyJestWZakresie30Dni(WykupionaWycieczka poprzedniaWycieczka) {
        // Parsujemy daty wyjazdu i powrotu
        LocalDate dataPowrotuPoprzedniej = LocalDate.parse(poprzedniaWycieczka.getData().getDataPowrotu());
        LocalDate dataWyjazduBiezacej = LocalDate.parse(this.getData().getDataWyjazdu());

        // Obliczamy różnicę dni między powrotem a wyjazdem
        long dniRoznicy = ChronoUnit.DAYS.between(dataPowrotuPoprzedniej, dataWyjazduBiezacej);
        return dniRoznicy > 0 && dniRoznicy <= 30; // Zwracamy true, jeśli różnica dni jest w przedziale 1-30
    }

    // Nalicza rabat, jeżeli poprzednia wycieczka była w ciągu 30 dni
    public void naliczRabatDlaKolejnych(WykupionaWycieczka poprzedniaWycieczka) {
        // Sprawdzamy, czy jest w przedziale 30 dni
        if (czyJestWZakresie30Dni(poprzedniaWycieczka)) {
            // Nalicza dodatkowy rabat 10%
            this.cenaZRabatem = wycieczka.getCena() * 0.9 * 0.9; // Kolejne 10% rabatu
        }
    }

    // Metoda zwracająca cenę po rabacie
    public double getCenaZRabatem() {
        return cenaZRabatem;
    }

    // Metoda toString do wyświetlania informacji o wykupionej wycieczce w czytelnej formie
    @Override
    public String toString() {
        return wycieczka.toString() + ", Klient: " + klient.getImie() + " " + klient.getNazwisko() + ", " + data.toString() + 
               ", Cena po rabacie: " + String.format("%.2f", cenaZRabatem) + " PLN";
    }
}