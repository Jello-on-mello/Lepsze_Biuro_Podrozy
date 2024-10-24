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
        // Proste sortowanie bąbelkowe
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

        // Klienci
        Klient klient1 = new Klient("Jan", "Kowalski", 30, "Warszawa");
        Klient klient2 = new Klient("Anna", "Nowak", 25, "Kraków");
        Klient klient3 = new Klient("Piotr", "Wiśniewski", 45, "Gdańsk");
        Klient klient4 = new Klient("Katarzyna", "Zielińska", 28, "Wrocław");
        Klient klient5 = new Klient("Michał", "Kowalczyk", 35, "Poznań");
        Klient klient6 = new Klient("Ewa", "Wojciechowska", 40, "Łódź");
        Klient klient7 = new Klient("Tomasz", "Kaczmarek", 32, "Szczecin");
        Klient klient8 = new Klient("Zofia", "Krawczyk", 29, "Lublin");
        Klient klient9 = new Klient("Jakub", "Szymański", 36, "Katowice");

        // Wycieczki
        Wycieczka wycieczka1 = new Wycieczka("Egipt", 3000, 5);
        Wycieczka wycieczka2 = new Wycieczka("Hiszpania", 2500, 3);
        Wycieczka wycieczka3 = new Wycieczka("Włochy", 3200, 2);
        Wycieczka wycieczka4 = new Wycieczka("Grecja", 2800, 7);
        Wycieczka wycieczka5 = new Wycieczka("Francja", 3500, 4);
        Wycieczka wycieczka6 = new Wycieczka("Turcja", 2600, 6);
        Wycieczka wycieczka7 = new Wycieczka("Norwegia", 4000, 10);
        Wycieczka wycieczka8 = new Wycieczka("Szwajcaria", 4500, 8);
        Wycieczka wycieczka9 = new Wycieczka("Portugalia", 2700, 5);

        // Daty
        Data data1 = new Data("2024-10-10", "2024-10-17");
        Data data2 = new Data("2024-11-01", "2024-11-08");
        Data data3 = new Data("2024-12-20", "2024-12-27");
        Data data4 = new Data("2024-05-10", "2024-05-17");
        Data data5 = new Data("2024-06-01", "2024-06-08");
        Data data6 = new Data("2024-07-15", "2024-07-22");
        Data data7 = new Data("2024-08-05", "2024-08-12");
        Data data8 = new Data("2024-09-15", "2024-09-22");
        Data data9 = new Data("2024-10-01", "2024-10-08");

        // Dodawanie wykupionych wycieczek do biura
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient1, wycieczka1, data1));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient2, wycieczka2, data2));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient3, wycieczka3, data3));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient4, wycieczka4, data4));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient5, wycieczka5, data5));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient6, wycieczka6, data6));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient7, wycieczka7, data7));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient8, wycieczka8, data8));
        biuro.dodajWykupionaWycieczke(new WykupionaWycieczka(klient9, wycieczka9, data9));

        biuro.generujRaporty();
    }
}
