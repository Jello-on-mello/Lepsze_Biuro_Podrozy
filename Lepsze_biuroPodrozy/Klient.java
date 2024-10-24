package Lepsze_biuroPodrozy;

/**
 * Klasa reprezentująca klienta biura podróży.
 */
public class Klient {
    private String imie;
    private String nazwisko;
    private int wiek;
    private String adres;

    /**
     * Konstruktor klasy Klient.
     *
     * @param imie imię klienta
     * @param nazwisko nazwisko klienta
     * @param wiek wiek klienta
     * @param adres adres klienta
     */
    public Klient(String imie, String nazwisko, int wiek, String adres) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.adres = adres;
    }

    /**
     * Zwraca imię klienta.
     *
     * @return imię klienta
     */
    public String getImie() {
        return imie;
    }

    /**
     * Zwraca nazwisko klienta.
     *
     * @return nazwisko klienta
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * Zwraca reprezentację obiektu jako ciąg znaków.
     *
     * @return łańcuch znaków reprezentujący klienta czyli jego pelne imie i nazwisko
     */
    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }
}
