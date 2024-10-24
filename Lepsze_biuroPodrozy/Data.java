package Lepsze_biuroPodrozy;

/**
 * Klasa reprezentująca daty wyjazdu i powrotu wycieczki.
 */
public class Data {
    private String dataWyjazdu;
    private String dataPowrotu;

    /**
     * Konstruktor klasy Data.
     *
     * @param dataWyjazdu data wyjazdu na wycieczkę
     * @param dataPowrotu data powrotu z wycieczki
     */
    public Data(String dataWyjazdu, String dataPowrotu) {
        this.dataWyjazdu = dataWyjazdu;
        this.dataPowrotu = dataPowrotu;
    }

    /**
     * Zwraca datę wyjazdu.
     *
     * @return data wyjazdu
     */
    public String getDataWyjazdu() {
        return dataWyjazdu;
    }

    /**
     * Zwraca datę powrotu.
     *
     * @return data powrotu
     */
    public String getDataPowrotu() {
        return dataPowrotu;
    }

    /**
     * Zwraca reprezentację obiektu jako ciąg znaków.
     *
     * @return łańcuch znaków reprezentujący daty wyjazdu i powrotu
     */
    @Override
    public String toString() {
        return "Wyjazd: " + dataWyjazdu + ", Powrót: " + dataPowrotu;
    }
}
