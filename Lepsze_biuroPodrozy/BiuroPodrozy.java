package Lepsze_biuroPodrozy;

// Importujemy potrzebne klasy
import java.util.ArrayList;
import java.util.Comparator;

public class BiuroPodrozy {
    // Lista wykupionych wycieczek
    private ArrayList<WykupionaWycieczka> wykupioneWycieczki = new ArrayList<>();

    // Metoda dodająca wykupioną wycieczkę do listy
    public void dodajWykupionaWycieczke(WykupionaWycieczka wykupionaWycieczka) {
        // Sprawdzanie, czy istnieje wycieczka tego samego klienta w ciągu 30 dni
        for (WykupionaWycieczka poprzedniaWycieczka : wykupioneWycieczki) {
            // Jeśli klient jest ten sam, naliczamy ewentualny rabat
            if (poprzedniaWycieczka.getKlient().equals(wykupionaWycieczka.getKlient())) {
                wykupionaWycieczka.naliczRabatDlaKolejnych(poprzedniaWycieczka);
            }
        }
        // Dodajemy wycieczkę do listy wykupionych wycieczek
        wykupioneWycieczki.add(wykupionaWycieczka);
    }

    // Metoda obliczająca sumę wszystkich zakupionych wycieczek z uwzględnieniem rabatów
    public double sumaWszystkichWycieczek() {
        double suma = 0;
        // Iteracja przez wszystkie wykupione wycieczki i sumowanie cen
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            suma += wykupiona.getCenaZRabatem(); // Dodajemy cenę po rabacie
        }
        return suma; // Zwracamy sumę
    }

    // Metoda wyświetlająca informacje o wszystkich wykupionych wycieczkach
    public void informacjeOWykupionychWycieczkach() {
        // Iteracja przez wszystkie wykupione wycieczki i wyświetlanie ich
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            System.out.println(wykupiona);
        }
    }

    // Metoda znajdująca klienta z najwyższą zapłaconą kwotą
    public void klientZNajwyzszaKwota() {
        // Znalezienie wycieczki o najwyższej cenie po rabacie
        WykupionaWycieczka najwiekszaKwota = wykupioneWycieczki.stream().max(Comparator.comparingDouble(WykupionaWycieczka::getCenaZRabatem)).orElse(null);
        // Jeśli znaleziono taką wycieczkę, wyświetlamy dane klienta
        if (najwiekszaKwota != null) {
            System.out.println("Klient z najwyższą kwotą: " + najwiekszaKwota.getKlient() +
                    ", Kwota: " + String.format("%.2f", najwiekszaKwota.getCenaZRabatem()) + " PLN");
        }
    }

    // Metoda wyświetlająca wycieczki posortowane według obrotów
    public void wycieczkiPosortowanePoObrotach() {
        // Sortowanie wykupionych wycieczek według ceny po rabacie w porządku malejącym
        wykupioneWycieczki.sort(Comparator.comparingDouble(WykupionaWycieczka::getCenaZRabatem).reversed());
        // Wyświetlanie posortowanych wycieczek
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            System.out.println(wykupiona);
        }
    }

    // Metoda generująca wszystkie raporty
    public void generujRaporty() {
        System.out.println("Suma wszystkich zakupionych wycieczek: " + sumaWszystkichWycieczek() + " PLN");
        System.out.println("\nInformacje o wszystkich wykupionych wycieczkach:");
        informacjeOWykupionychWycieczkach();
        System.out.println("\nKlient z najwyższą zapłaconą kwotą:");
        klientZNajwyzszaKwota();
        System.out.println("\nWycieczki posortowane według obrotów:");
        wycieczkiPosortowanePoObrotach();
    }

    // Metoda main - punkt startowy aplikacji
    public static void main(String[] args) {
        // Tworzymy nowe biuro podróży
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

        // Dodawanie wykupionych wycieczek do biura
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient1, wycieczka1, data1));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient1, wycieczka2, data2)); // Powinna mieć dodatkowy rabat
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient2, wycieczka3, data3));

        // Generowanie raportów
        biuro.generujRaporty();
    }
}