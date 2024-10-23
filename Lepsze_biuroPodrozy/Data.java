package Lepsze_biuroPodrozy;

// Klasa reprezentująca daty wyjazdu i powrotu
public class Data {
    private String dataWyjazdu; // Data wyjazdu
    private String dataPowrotu; // Data powrotu

    // Konstruktor klasy Data
    public Data(String dataWyjazdu, String dataPowrotu) {
        this.dataWyjazdu = dataWyjazdu;
        this.dataPowrotu = dataPowrotu;
    }

    // Metoda zwracająca datę wyjazdu
    public String getDataWyjazdu() {
        return dataWyjazdu;
    }

    // Metoda zwracająca datę powrotu
    public String getDataPowrotu() {
        return dataPowrotu;
    }

    // Metoda toString do wyświetlania daty w czytelnej formie
    @Override
    public String toString() {
        return "Wyjazd: " + dataWyjazdu + ", Powrót: " + dataPowrotu;
    }
}
