package Lepsze_biuroPodrozy;

public class Wycieczka {
    private String opisPodrozy;
    private double cena;
    private int iloscMiejsc;

    public Wycieczka(String opisPodrozy, double cena, int iloscMiejsc) {
        this.opisPodrozy = opisPodrozy;
        this.cena = cena;
        this.iloscMiejsc = iloscMiejsc;
    }

    public double getCena() {
        return cena;
    }

    public String getOpisPodrozy() {
        return opisPodrozy;
    }

    public int getIloscMiejsc() {
        return iloscMiejsc;
    }

    @Override
    public String toString() {
        return "Wycieczka: " + opisPodrozy + ", Cena: " + String.format("%.2f", cena) + " PLN, Miejsca: " + iloscMiejsc;
    }
}
