package Lepsze_biuroPodrozy;

/**
 * Klasa reprezentująca biuro podróży, zarządza wykupionymi wycieczkami.
 * Pozwala na dodawanie wycieczek, naliczanie rabatów, generowanie raportów.
 */
public class BiuroPodrozy {
    private WykupionaWycieczka[] wykupioneWycieczki = new WykupionaWycieczka[10];  // Początkowy rozmiar tablicy
    private int liczbaWycieczek = 0;

    /**
     * Dodaje wykupioną wycieczkę do tablicy. Jeżeli zabraknie miejsca w tablicy,
     * zostanie ona automatycznie powiększona.
     * Jeżeli klient wykupił poprzednią wycieczkę w ciągu 30 dni, zostaje naliczony rabat.
     *
     * @param wykupionaWycieczka wykupiona wycieczka przez klienta
     */
    public void dodajWykupionaWycieczke(WykupionaWycieczka wykupionaWycieczka) {
        // Sprawdź, czy trzeba rozszerzyć tablicę
        if (liczbaWycieczek == wykupioneWycieczki.length) {
            rozszerzTabliceWycieczek();
        }

        // Sprawdzanie rabatu
        for (int i = 0; i < liczbaWycieczek; i++) {
            WykupionaWycieczka poprzedniaWycieczka = wykupioneWycieczki[i];
            if (poprzedniaWycieczka.getKlient().equals(wykupionaWycieczka.getKlient())) {
                wykupionaWycieczka.naliczRabatDlaKolejnych(poprzedniaWycieczka);
            }
        }

        wykupioneWycieczki[liczbaWycieczek] = wykupionaWycieczka;
        liczbaWycieczek++;
    }

    /**
     * Wyświetla wszystkie wykupione wycieczki dla danego klienta.
     *
     * @param klient Klient, dla którego chcemy wyświetlić wykupione wycieczki.
     */
    public void wyswietlWycieczkiDlaKlienta(Klient klient) {
        boolean znalezionoWycieczki = false;

        System.out.println("Wycieczki wykupione przez klienta: " + klient.getImie() + " " + klient.getNazwisko());

        for (int i = 0; i < liczbaWycieczek; i++) {
            if (wykupioneWycieczki[i].getKlient().equals(klient)) {
                System.out.println(wykupioneWycieczki[i]);
                znalezionoWycieczki = true;
            }
        }

        if (!znalezionoWycieczki) {
            System.out.println("Brak wykupionych wycieczek dla tego klienta.");
        }
    }

    /**
     * Wyświetla wszystkie wykupione wycieczki dla wszystkich unikalnych klientów.
     */
    public void wyswietlWycieczkiDlaWszystkichKlientow() {
        for (int i = 0; i < liczbaWycieczek; i++) {
            Klient klient = wykupioneWycieczki[i].getKlient();
            System.out.println("\nWycieczki dla klienta: " + klient.getImie() + " " + klient.getNazwisko());
            wyswietlWycieczkiDlaKlienta(klient);
        }
    }

    /**
     * Rozszerza tablicę wykupionych wycieczek, kiedy zabraknie w niej miejsca.
     */
    private void rozszerzTabliceWycieczek() {
        WykupionaWycieczka[] nowaTablica = new WykupionaWycieczka[wykupioneWycieczki.length * 2];
        System.arraycopy(wykupioneWycieczki, 0, nowaTablica, 0, wykupioneWycieczki.length);
        wykupioneWycieczki = nowaTablica;
    }

    /**
     * Oblicza sumę wszystkich wykupionych wycieczek z uwzględnieniem rabatów.
     *
     * @return suma cen wycieczek po rabatach
     */
    public double sumaWszystkichWycieczek() {
        double suma = 0;
        for (int i = 0; i < liczbaWycieczek; i++) {
            suma += wykupioneWycieczki[i].getCenaZRabatem();
        }
        return suma;
    }

    /**
     * Wyświetla informacje o wszystkich wykupionych wycieczkach.
     */
    public void informacjeOWykupionychWycieczkach() {
        for (int i = 0; i < liczbaWycieczek; i++) {
            System.out.println(wykupioneWycieczki[i]);
        }
    }

    /**
     * Znajduje klienta z najwyższą zapłaconą kwotą za wycieczkę i wyświetla jego dane.
     */
    public void klientZNajwyzszaKwota() {
        WykupionaWycieczka najwiekszaKwota = null;
        for (int i = 0; i < liczbaWycieczek; i++) {
            if (najwiekszaKwota == null || wykupioneWycieczki[i].getCenaZRabatem() > najwiekszaKwota.getCenaZRabatem()) {
                najwiekszaKwota = wykupioneWycieczki[i];
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
        for (int i = 0; i < liczbaWycieczek - 1; i++) {
            for (int j = 0; j < liczbaWycieczek - i - 1; j++) {
                if (wykupioneWycieczki[j].getCenaZRabatem() < wykupioneWycieczki[j + 1].getCenaZRabatem()) {
                    WykupionaWycieczka temp = wykupioneWycieczki[j];
                    wykupioneWycieczki[j] = wykupioneWycieczki[j + 1];
                    wykupioneWycieczki[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < liczbaWycieczek; i++) {
            System.out.println(wykupioneWycieczki[i]);
        }
    }

    /**
     * Generuje raporty dotyczące sumy wycieczek, informacji o klientach
     * oraz posortowanych wycieczkach według obrotów.
     */
    public void generujRaporty() {
        System.out.println("Suma wszystkich zakupionych wycieczek: " + sumaWszystkichWycieczek() + " PLN");

        System.out.println("\n-----------------------------------------------------------------\n");

        System.out.println("\nInformacje o wszystkich wykupionych wycieczkach:");
        informacjeOWykupionychWycieczkach();

        System.out.println("\n-----------------------------------------------------------------\n");

        System.out.println("\nKlient z najwyższą zapłaconą kwotą:");
        klientZNajwyzszaKwota();
        System.out.println("\n-----------------------------------------------------------------\n");

        System.out.println("\nWycieczki posortowane według obrotów:");
        wycieczkiPosortowanePoObrotach();

        System.out.println("\n-----------------------------------------------------------------\n");

        System.out.println("\nWycieczki wykupione przez poszczególnych klientów:");
        wyswietlWycieczkiDlaWszystkichKlientow();
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
