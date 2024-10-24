package Lepsze_biuroPodrozy;

import java.util.ArrayList;

/**
 * Klasa reprezentująca biuro podróży, zarządza wykupionymi wycieczkami.
 * Pozwala na dodawanie wycieczek, naliczanie rabatów, generowanie raportów.
 */
public class BiuroPodrozy {
    private ArrayList<WykupionaWycieczka> wykupioneWycieczki = new ArrayList<>();

    /**
     * Dodaje wykupioną wycieczkę do listy.
     * Jeżeli klient wykupił poprzednią wycieczkę w ciągu 30 dni, zostaje naliczony rabat.
     *
     * @param wykupionaWycieczka wykupiona wycieczka przez klienta
     */
    public void dodajWykupionaWycieczke(WykupionaWycieczka wykupionaWycieczka) {
        for (WykupionaWycieczka poprzedniaWycieczka : wykupioneWycieczki) {
            if (poprzedniaWycieczka.getKlient().equals(wykupionaWycieczka.getKlient())) {
                wykupionaWycieczka.naliczRabatDlaKolejnych(poprzedniaWycieczka);
            }
        }
        wykupioneWycieczki.add(wykupionaWycieczka);
    }

    /**
     * Oblicza sumę wszystkich wykupionych wycieczek z uwzględnieniem rabatów.
     *
     * @return suma cen wycieczek po rabatach
     */
    public double sumaWszystkichWycieczek() {
        double suma = 0;
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            suma += wykupiona.getCenaZRabatem();
        }
        return suma;
    }

    /**
     * Wyświetla informacje o wszystkich wykupionych wycieczkach.
     */
    public void informacjeOWykupionychWycieczkach() {
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            System.out.println(wykupiona);
        }
    }

    /**
     * Znajduje klienta z najwyższą zapłaconą kwotą za wycieczkę i wyświetla jego dane.
     */
    public void klientZNajwyzszaKwota() {
        WykupionaWycieczka najwiekszaKwota = null;
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            if (najwiekszaKwota == null || wykupiona.getCenaZRabatem() > najwiekszaKwota.getCenaZRabatem()) {
                najwiekszaKwota = wykupiona;
            }
        }
        if (najwiekszaKwota != null) {
            System.out.println("Klient z najwyższą kwotą: " + najwiekszaKwota.getKlient() +
                    ", Kwota: " + String.format("%.2f", najwiekszaKwota.getCenaZRabatem()) + " PLN");
        }
    }

    /**
     * Wyświetla listę wycieczek posortowanych według wartości obrotów (od najwyższej).
     */
    public void wycieczkiPosortowanePoObrotach() {
        // Proste sortowanie bąbelkowe bez użycia Comparatora
        int n = wykupioneWycieczki.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (wykupioneWycieczki.get(j).getCenaZRabatem() < wykupioneWycieczki.get(j + 1).getCenaZRabatem()) {
                    WykupionaWycieczka temp = wykupioneWycieczki.get(j);
                    wykupioneWycieczki.set(j, wykupioneWycieczki.get(j + 1));
                    wykupioneWycieczki.set(j + 1, temp);
                }
            }
        }
        for (WykupionaWycieczka wykupiona : wykupioneWycieczki) {
            System.out.println(wykupiona);
        }
    }

    /**
     * Generuje raporty dotyczące sumy wycieczek, informacji o klientach
     * oraz posortowanych wycieczkach według obrotów.
     */
    public void generujRaporty() {
        System.out.println("Suma wszystkich zakupionych wycieczek: " + sumaWszystkichWycieczek() + " PLN");
        System.out.println("\nInformacje o wszystkich wykupionych wycieczkach:");
        informacjeOWykupionychWycieczkach();
        System.out.println("\nKlient z najwyższą zapłaconą kwotą:");
        klientZNajwyzszaKwota();
        System.out.println("\nWycieczki posortowane według obrotów:");
        wycieczkiPosortowanePoObrotach();
    }

    /**
     * Punkt startowy programu.
     *
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        BiuroPodrozy biuro = new BiuroPodrozy();

        Klient klient1 = new Klient("Jan", "Kowalski", 30, "Warszawa");
        Klient klient2 = new Klient("Anna", "Nowak", 25, "Kraków");
        Klient klient3 = new Klient("Piotr", "Wiśniewski", 45, "Gdańsk");

        Wycieczka wycieczka1 = new Wycieczka("Egipt", 3000, 5);
        Wycieczka wycieczka2 = new Wycieczka("Hiszpania", 2500, 3);
        Wycieczka wycieczka3 = new Wycieczka("Włochy", 3200, 2);

        Data data1 = new Data("2024-10-10", "2024-10-17");
        Data data2 = new Data("2024-11-01", "2024-11-08");
        Data data3 = new Data("2024-12-20", "2024-12-27");

        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient1, wycieczka1, data1));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient1, wycieczka2, data2));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient2, wycieczka3, data3));

        biuro.generujRaporty();
    }
}
