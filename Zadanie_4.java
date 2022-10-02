import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.*;

public class Zadanie_4 {
    private final static String KOMUNIKAT_OKEJ = "\033[38;2;11;200;30mOKEJ\033[0m";
    private final static String KOMUNIKAT_BŁĄD = "\033[38;2;200;11;11mBŁĄD\033[0m";



    public static void main(String[] args) throws Exception {
        Scanner skaner = new Scanner(System.in);
        ListaProduktów produkty = new ListaProduktów("Zakupy.txt");

        while (true) {
            System.out.println("1) Dodaj produkt");
            System.out.println("2) Usuń produkt");
            System.out.println("3) Usuń wszystkie produkty");
            System.out.println("4) Wyświetl wszystkie produkty");
            System.out.println("0) Zakończ");
            System.out.print("> ");
            int wybór = skaner.nextInt();
            if (wybór == 1) {
                if (dodajProdukt(produkty, skaner)) {
                    System.out.println(KOMUNIKAT_OKEJ);
                } else {
                    System.out.println(KOMUNIKAT_BŁĄD);
                }
            } else if (wybór == 2) {
                if (usuńProdukt(produkty, skaner)) {
                    System.out.println(KOMUNIKAT_OKEJ);
                } else {
                    System.out.println(KOMUNIKAT_BŁĄD);
                }
            } else if (wybór == 3) {
                produkty.clear();
                System.out.println(KOMUNIKAT_OKEJ);
            } else if (wybór == 4) {
                produkty.wypisz();
            } else if (wybór == 0) {
                break;
            } else {
                System.out.println(KOMUNIKAT_BŁĄD);
            }
            produkty.zapisz();
        }
    }

    private static boolean dodajProdukt(ListaProduktów produkty, Scanner skaner) {
        System.out.println("Nazwa produktu: ");
        skaner.nextLine();
        String nazwaProduktu = skaner.nextLine();
        System.out.println("Nazwa kategorii: ");
        String nazwaKategorii = skaner.nextLine();
        Kategoria kategoria = znajdźKategorie(produkty, nazwaKategorii);
        Produkt produkt = new Produkt(nazwaProduktu, kategoria);
        produkty.add(produkt);
        return true;
    }

    private static boolean usuńProdukt(ListaProduktów produkty, Scanner skaner) {
        if (skaner.hasNext()) {
            System.out.print("Numer produktu ");
            int numerProduktu = skaner.nextInt();
            Produkt produkt = znajdźProdukt(produkty, numerProduktu);
            if (produkt != null) {
                produkty.remove(produkt);
                return true;
            }
        }
        return false;

    }

    private static Kategoria znajdźKategorie(ListaProduktów produkty, String nazwaKategorii) {
        for (Produkt produkt : produkty) {
            if (produkt.getKategoria().getNazwa().equals(nazwaKategorii)) {
                return produkt.getKategoria();
            }
        }
        return new Kategoria(nazwaKategorii);
    }


    private static Produkt znajdźProdukt(ListaProduktów produkty, int numerProduktu) {
        int lp = 1;
        for (Produkt produkt : produkty) {
            if (lp++ == numerProduktu) {
                return produkt;
            }
        }
        return null;
    }
}



class Kategoria {
    private String nazwa;

    public Kategoria(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }
}

class Produkt {
    private String nazwa;
    private Kategoria kategoria;

    public Produkt(String nazwa, Kategoria kategoria) {
        this.nazwa = nazwa;
        this.kategoria = kategoria;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }
}

class ListaProduktów  extends ArrayList<Produkt> {
    private String ścieżka;

    public ListaProduktów(String ścieżka) throws Exception {
        this.ścieżka = ścieżka;
        wczytajProdukty();
    }


    public void wypisz() {
        System.out.printf("%3s %-30s %s\n", "Lp.", "Nazwa", "Kategoria");
        System.out.println("-".repeat(80));
        int lp = 1;
        for (Produkt produkt : this) {
            System.out.printf("%3d %-30s %s\n", lp++, produkt.getNazwa(), produkt.getKategoria().getNazwa());
        }
        System.out.println("-".repeat(80));
    }

    public void zapisz() throws Exception {
        Map<String, List<Produkt>> kategorie = new TreeMap<>();
        for (Produkt produkt : this) {
            String nazwaKategorii = produkt.getKategoria().getNazwa();
            if (!kategorie.containsKey(nazwaKategorii)) {
                kategorie.put(nazwaKategorii, new ArrayList<>());
            }
            kategorie.get(nazwaKategorii).add(produkt);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(ścieżka));
        for (String nazwaKategorii : kategorie.keySet()) {
            writer.write(nazwaKategorii + "\n");
            for (Produkt produkt : kategorie.get(nazwaKategorii)) {
                writer.write("\t" + produkt.getNazwa() + "\n");
            }
        }
        writer.close();
    }



    private void wczytajProdukty() throws Exception {
        BufferedReader czytacz = new BufferedReader(new FileReader(ścieżka));
        String line;
        Kategoria aktualnaKategoria = null;

        while ((line = czytacz.readLine()) != null) {
            if (!Character.isWhitespace(line.charAt(0))) {
                aktualnaKategoria = new Kategoria(line);
            } else {
                add(new Produkt(line.trim(), aktualnaKategoria));
            }
        }
    }
}
