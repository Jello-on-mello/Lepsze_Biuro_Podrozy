package Lepsze_biuroPodrozy;

/**
 * Klasa reprezentująca wycieczkę.
 */
public class Wycieczka {
    private String opisPodrozy;
    private double cena;
    private int iloscMiejsc;

    /**
     * Konstruktor klasy Wycieczka.
     *
     * @param opisPodrozy opis wycieczki
     * @param cena cena wycieczki
     * @param iloscMiejsc ilość dostępnych miejsc
     */
    public Wycieczka(String opisPodrozy, double cena, int iloscMiejsc) {
        this.opisPodrozy = opisPodrozy;
        this.cena = cena;
        this.iloscMiejsc = iloscMiejsc;
    }

    /**
     * Zwraca cenę wycieczki.
     *
     * @return cena wycieczki
     */
    public double getCena() {
        return cena;
    }

    /**
     * Zwraca opis wycieczki.
     *
     * @return opis wycieczki
     */
    public String getOpisPodrozy() {
        return opisPodrozy;
    }

    /**
     * Zwraca ilość miejsc na wycieczkę.
     *
     * @return ilość miejsc
     */
    public int getIloscMiejsc() {
        return iloscMiejsc;
    }

    /**
     * Zwraca reprezentację obiektu jako ciąg znaków.
     *
     * @return łańcuch znaków reprezentujący wycieczkę
     */
    @Override
    public String toString() {
        return "Wycieczka: " + opisPodrozy + ", Cena: " + String.format("%.2f", cena) + " PLN, Miejsca: " + iloscMiejsc;
    }
}
