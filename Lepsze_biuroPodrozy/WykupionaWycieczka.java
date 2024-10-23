import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WykupionaWycieczka {
    private Klient klient;
    private Wycieczka wycieczka;
    private Data data;
    private double cenaZRabatem;

    public WykupionaWycieczka(Klient klient, Wycieczka wycieczka, Data data) {
        this.klient = klient;
        this.wycieczka = wycieczka;
        this.data = data;
        this.cenaZRabatem = wycieczka.getCena() * 0.9; // Początkowy rabat 10%
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

    // Sprawdza, czy dwie wycieczki są w odstępie 30 dni
    public boolean czyJestWZakresie30Dni(WykupionaWycieczka poprzedniaWycieczka) {
        LocalDate dataPowrotuPoprzedniej = LocalDate.parse(poprzedniaWycieczka.getData().getDataPowrotu());
        LocalDate dataWyjazduBiezacej = LocalDate.parse(this.getData().getDataWyjazdu());

        long dniRoznicy = ChronoUnit.DAYS.between(dataPowrotuPoprzedniej, dataWyjazduBiezacej);
        return dniRoznicy > 0 && dniRoznicy <= 30;
    }

    // Nalicza rabat, jeżeli poprzednia wycieczka była w ciągu 30 dni
    public void naliczRabatDlaKolejnych(WykupionaWycieczka poprzedniaWycieczka) {
        if (czyJestWZakresie30Dni(poprzedniaWycieczka)) {
            this.cenaZRabatem = wycieczka.getCena() * 0.9 * 0.9; // Kolejne 10% rabatu
        }
    }

    public double getCenaZRabatem() {
        return cenaZRabatem;
    }

    @Override
    public String toString() {
        return wycieczka.toString() + ", Klient: " + klient.getImie() + " " + klient.getNazwisko() + ", " + data.toString() + 
               ", Cena po rabacie: " + String.format("%.2f", cenaZRabatem) + " PLN";
    }
}
