package Lepsze_biuroPodrozy;

public class WykupionaWycieczka {
    private Klient klient;
    private Wycieczka wycieczka;
    private Data data;

    public WykupionaWycieczka(Klient klient, Wycieczka wycieczka, Data data) {
        this.klient = klient;
        this.wycieczka = wycieczka;
        this.data = data;
    }

    public Klient getKlient() {
        return klient;
    }

    public Wycieczka getWycieczka() {
        return wycieczka;
    }

    public Data getData() {
        return data;
    }

    public double getCena() {
        return wycieczka.getCena() * 0.9;  // Rabat 10%
    }

    @Override
    public String toString() {
        return wycieczka.toString() + ", Klient: " + klient.getImie() + " " + klient.getNazwisko() + ", " + data.toString();
    }
}
