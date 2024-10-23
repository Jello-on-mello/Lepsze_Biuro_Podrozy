import java.util.ArrayList;
import java.util.Comparator;

public class BiuroPodrozy {
    private ArrayList<WykupionaWycieczka> wykupioneWycieczki = new ArrayList<>();

    public void dodajWykupionaWycieczke(WykupionaWycieczka wykupionaWycieczka) {
        // Sprawdzanie, czy istnieje wycieczka tego samego klienta w ciągu 30 dni
        for (WykupionaWycieczka poprzedniaWycieczka : wykupioneWycieczki) {
            if (poprzedniaWycieczka.getKlient().equals(wykupionaWycieczka.getKlient())) {
                wykupionaWycieczka.naliczRabatDlaKolejnych(poprzedniaWycieczka);
            }
        }
        wykupioneWycieczki.add(wykupionaWycieczka);
    }

    // Raport: Suma wszystkich zakupionych wycieczek (z uwzględnieniem rabatów)
    public double sumaWszystkichWycieczek() {
        double suma = 0;
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            suma += wykupiona.getCenaZRabatem();
        }
        return suma;
    }

    // Raport: Informacje o wszystkich wykupionych wycieczkach
    public void informacjeOWykupionychWycieczkach() {
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            System.out.println(wykupiona);
        }
    }

    // Raport: Klient z najwyższą zapłaconą kwotą
    public void klientZNajwyzszaKwota() {
        WykupionaWycieczka najwiekszaKwota = wykupioneWycieczki.stream().max(Comparator.comparingDouble(WykupionaWycieczka::getCenaZRabatem)).orElse(null);
        if (najwiekszaKwota != null) {
            System.out.println("Klient z najwyższą kwotą: " + najwiekszaKwota.getKlient() +
                    ", Kwota: " + String.format("%.2f", najwiekszaKwota.getCenaZRabatem()) + " PLN");
        }
    }

    // Raport: Wycieczki posortowane według obrotów (suma)
    public void wycieczkiPosortowanePoObrotach() {
        wykupioneWycieczki.sort(Comparator.comparingDouble(WykupionaWycieczka::getCenaZRabatem).reversed());
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            System.out.println(wykupiona);
        }
    }

    public void generujRaporty() {
        System.out.println("Suma wszystkich zakupionych wycieczek: " + sumaWszystkichWycieczek() + " PLN");
        System.out.println("\nInformacje o wszystkich wykupionych wycieczkach:");
        informacjeOWykupionychWycieczkach();
        System.out.println("\nKlient z najwyższą zapłaconą kwotą:");
        klientZNajwyzszaKwota();
        System.out.println("\nWycieczki posortowane według obrotów:");
        wycieczkiPosortowanePoObrotach();
    }

    public static void main(String[] args) {
        BiuroPodrozy biuro = new BiuroPodrozy();

        // Przykładowi klienci
        Klient klient1 = new Klient("Jan", "Kowalski", 30, "Warszawa");
        Klient klient2 = new Klient("Anna", "Nowak", 25, "Kraków");
        Klient klient3 = new Klient("Piotr", "Wiśniewski", 45, "Gdańsk");

        // Przykładowe wycieczki
        Wycieczka wycieczka1 = new Wycieczka("Egipt", 3000, 5);
        Wycieczka wycieczka2 = new Wycieczka("Hiszpania", 2500, 3);
        Wycieczka wycieczka3 = new Wycieczka("Włochy", 3200, 2);

        // Przykładowe daty wycieczek
        Data data1 = new Data("2024-10-10", "2024-10-17");
        Data data2 = new Data("2024-11-01", "2024-11-08");
        Data data3 = new Data("2024-12-20", "2024-12-27");

        // Wykupione wycieczki
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient1, wycieczka1, data1));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient1, wycieczka2, data2)); // Powinna mieć dodatkowy rabat
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient2, wycieczka3, data3));

        // Generowanie raportów
        biuro.generujRaporty();
    }
}
