package Lepsze_biuroPodrozy;

public class Data {
    private String dataWyjazdu;
    private String dataPowrotu;

    public Data(String dataWyjazdu, String dataPowrotu) {
        this.dataWyjazdu = dataWyjazdu;
        this.dataPowrotu = dataPowrotu;
    }

    public String getDataWyjazdu() {
        return dataWyjazdu;
    }

    public String getDataPowrotu() {
        return dataPowrotu;
    }

    @Override
    public String toString() {
        return "Wyjazd: " + dataWyjazdu + ", Powrót: " + dataPowrotu;
    }
}
