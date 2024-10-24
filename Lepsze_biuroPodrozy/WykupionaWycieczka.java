package Lepsze_biuroPodrozy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Klasa reprezentująca wykupioną wycieczkę przez klienta.
 */
public class WykupionaWycieczka {
    private Klient klient;
    private Wycieczka wycieczka;
    private Data data;
    private double cenaZRabatem;

    /**
     * Konstruktor klasy WykupionaWycieczka.
     *
     * @param klient klient wykupujący wycieczkę
     * @param wycieczka wycieczka, którą wykupił klient
     * @param data daty wyjazdu i powrotu
     */
    public WykupionaWycieczka(Klient klient, Wycieczka wycieczka, Data data) {
        this.klient = klient;
        this.wycieczka = wycieczka;
        this.data = data;
        this.cenaZRabatem = wycieczka.getCena() * 0.9; // Początkowy rabat 10%
    }

    /**
     * Zwraca klienta, który wykupił wycieczkę.
     *
     * @return klient wykupujący wycieczkę
     */
    public Klient getKlient() {
        return klient;
    }

    /**
     * Zwraca wycieczkę, którą wykupił klient.
     *
     * @return wykupiona wycieczka
     */
    public Wycieczka getWycieczka() {
        return wycieczka;
    }

    /**
     * Zwraca daty wycieczki.
     *
     * @return daty wycieczki
     */
    public Data getData() {
        return data;
    }

    /**
     * Sprawdza, czy dwie wycieczki są w odstępie 30 dni.
     *
     * @param poprzedniaWycieczka poprzednia wykupiona wycieczka klienta
     * @return true, jeżeli różnica dni jest w przedziale 1-30
     */
    public boolean czyJestWZakresie30Dni(WykupionaWycieczka poprzedniaWycieczka) {
        LocalDate dataPowrotuPoprzedniej = LocalDate.parse(poprzedniaWycieczka.getData().getDataPowrotu());
        LocalDate dataWyjazduBiezacej = LocalDate.parse(this.getData().getDataWyjazdu());
        long dniRoznicy = ChronoUnit.DAYS.between(dataPowrotuPoprzedniej, dataWyjazduBiezacej);
        return dniRoznicy > 0 && dniRoznicy <= 30;
    }

    /**
     * Naliczanie rabatu dla kolejnych wycieczek w przypadku zakupu w ciągu 30 dni od poprzedniej.
     *
     * @param poprzedniaWycieczka poprzednia wykupiona wycieczka klienta
     */
    public void naliczRabatDlaKolejnych(WykupionaWycieczka poprzedniaWycieczka) {
        if (czyJestWZakresie30Dni(poprzedniaWycieczka)) {
            this.cenaZRabatem = wycieczka.getCena() * 0.9 * 0.9;
        }
    }

    /**
     * Zwraca cenę wycieczki po naliczonym rabacie.
     *
     * @return cena wycieczki po rabacie
     */
    public double getCenaZRabatem() {
        return cenaZRabatem;
    }

    /**
     * Zwraca reprezentację obiektu jako ciąg znaków.
     *
     * @return łańcuch znaków reprezentujący wykupioną wycieczkę
     */
    @Override
    public String toString() {
        return wycieczka.toString() + ", Klient: " + klient.getImie() + " " + klient.getNazwisko() +
               ", " + data.toString() + ", Cena po rabacie: " + String.format("%.2f", cenaZRabatem) + " PLN";
    }
}
