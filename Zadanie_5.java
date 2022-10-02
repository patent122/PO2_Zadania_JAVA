import java.util.*;

public class Zadanie_5 {
    public static void main(String[] args) {
        List<NrTelefoniczny> numery = new ArrayList<>();

        numery.add(new NrTelefoniczny(48, 123456789));
        numery.add(new NrTelefoniczny(22, 444555666));
        numery.add(new NrTelefoniczny(22, 987654321));
        numery.add(new NrTelefoniczny(48, 111222333));
        Collections.sort(numery);
        System.out.println(numery);

        Osoba osoba1 = new Osoba("Józef", "talar", "Nieznana 29, 00-111 Nieznanów", new NrTelefoniczny(48, 333333333));
        Firma firma1 = new Firma("StarGres", "Górna 5, 26-200 Końskie", new NrTelefoniczny(48, 708732612));
        Osoba osoba2 = new Osoba("Józef", "talar", "Nieznana 11, 00-111 Nieznanów", new NrTelefoniczny(48, 555555555));
        Firma firma2 = new Firma("StarGres", "Rolna 5, 26-200 Końskie", new NrTelefoniczny(48, 708732616));
        Osoba osoba3 = new Osoba("Józef", "talar", "Długa 12, 00-111 Nieznanów", new NrTelefoniczny(48, 777777777));
        Firma firma3 = new Firma("StarGres", "Polna 5, 26-200 Końskie", new NrTelefoniczny(48, 708732618));


        TreeMap<NrTelefoniczny, Wpis> wpisy = new TreeMap<>();
        wpisy.put(osoba1.getNrTelefoniczny(), osoba1);
        wpisy.put(firma1.getNrTelefoniczny(), firma1);
        wpisy.put(osoba2.getNrTelefoniczny(), osoba2);
        wpisy.put(firma2.getNrTelefoniczny(), firma2);
        wpisy.put(osoba3.getNrTelefoniczny(), osoba3);
        wpisy.put(firma3.getNrTelefoniczny(), firma3);

        // pobieramy iterator kolekcji
        Iterator<Map.Entry<NrTelefoniczny, Wpis>> iterator = wpisy.entrySet().iterator();
        // czy jest kolejny element w iteratorze?
        while (iterator.hasNext()) {
            // pobierz kolejny element
            Map.Entry<NrTelefoniczny, Wpis> wpis = iterator.next();
            // wypisz element w formacie: KLUCZ => WARTOŚĆ (nrTelefonu => opis)
            System.out.println(wpis.getKey() + "=> " + wpis.getValue().opis());
        }

        Iterator<Map.Entry<NrTelefoniczny, Wpis>> i1 = wpisy.entrySet().iterator();
        // przechodzimy po całej kolekcji (pętla zewnętrzna)
        while (i1.hasNext()) {
            // pobieramy element testowany pod względem identyczności nazwy ulicy
            Map.Entry<NrTelefoniczny, Wpis> e1 = i1.next();
            Iterator<Map.Entry<NrTelefoniczny, Wpis>> i2 = wpisy.entrySet().iterator();
            boolean czyUsunąć = false;
            // przechodzimy po całej kolekcji (pętla węwntętrzna)
            while (i2.hasNext()) {
                // pobieramy element testujący
                Map.Entry<NrTelefoniczny, Wpis> e2 = i2.next();
                // 1) sprawdzamy czy nie porównujemy wpisu ze samym sobą
                if (!e1.getKey().equals(e2.getKey())
                        // 2) tutaj w końcu właściwe sprawdzenie identyczności ulic
                        && takSamaUlica(e1.getValue(), e2.getValue())) {
                    // nie możemy tutaj usuwać ponieważ iteratory i1 i i2 "idą po tej samej kolekcji
                    // i otrzymamy wyjątek (nie można modyfikować kolekcji, gdy "idą" po niej w tym samym
                    // momencie dwa lub więcej iteratorów)
                    czyUsunąć = true;
                    break;
                }
            }
            i1.remove();
        }
        System.out.println();
        iterator = wpisy.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<NrTelefoniczny, Wpis> wpis = iterator.next();
            System.out.println(wpis.getKey() + " => " + wpis.getValue().opis());
        }
    }

    private static boolean takSamaUlica(Wpis a, Wpis b) {
        return true;
    }
}

class NrTelefoniczny implements Comparable<NrTelefoniczny> {
    private int nrKierunkowy;
    private int nrTelefonu;

    public NrTelefoniczny(int nrKierunkowy, int nrTelefonu) {
        this.nrKierunkowy = nrKierunkowy;
        this.nrTelefonu = nrTelefonu;
    }

    public int getNrKierunkowy() {
        return nrKierunkowy;
    }

    public void setNrKierunkowy(int nrKierunkowy) {
        this.nrKierunkowy = nrKierunkowy;
    }

    public int getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(int nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public String toString() {
        return String.format("+%d %d", nrKierunkowy, nrTelefonu);
    }

    // -1 gdy obiekt 'this' jest mniejszy od obiektu 'inny'
    // 0 gdy są równe
    // 1 gdy obiekt 'this' jest większyy od obiektu 'inny'
    @Override
    public int compareTo(NrTelefoniczny inny) {
        if (this.nrKierunkowy < inny.nrKierunkowy) {
            return -1;
        } else if (nrKierunkowy > inny.nrKierunkowy) {
            return 1;
        }

        if (this.nrTelefonu < inny.nrTelefonu) {
            return -1;
        } else if (this.nrTelefonu > inny.nrTelefonu) {
            return 1;
        }
        return 0;
    }
}

abstract class Wpis {
    public abstract String opis();

}

class Osoba extends Wpis{
    private String imię;
    private String nazwisko;
    private String adres;
    private NrTelefoniczny nrTelefoniczny;

    public Osoba(String imię, String nazwisko, String adres, NrTelefoniczny nrTelefoniczny) {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.nrTelefoniczny = nrTelefoniczny;
    }

    public String getImię() {
        return imię;
    }

    public void setImię(String imię) {
        this.imię = imię;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public NrTelefoniczny getNrTelefoniczny() {
        return nrTelefoniczny;
    }

    public void setNrTelefoniczny(NrTelefoniczny nrTelefoniczny) {
        this.nrTelefoniczny = nrTelefoniczny;
    }

    @Override
    public String opis() {
        return String.format("%s %s ul. %s (%s)", imię, nazwisko, adres, nrTelefoniczny);
    }
}

class Firma extends Wpis {
    private String nazwa;
    private String adres;
    private NrTelefoniczny nrTelefoniczny;

    public Firma(String nazwa, String adres, NrTelefoniczny nrTelefoniczny) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.nrTelefoniczny = nrTelefoniczny;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public NrTelefoniczny getNrTelefoniczny() {
        return nrTelefoniczny;
    }

    public void setNrTelefoniczny(NrTelefoniczny nrTelefoniczny) {
        this.nrTelefoniczny = nrTelefoniczny;
    }

    @Override
    public String opis() {
        return String.format("%s ul. %s (%s)", nazwa, adres, nrTelefoniczny);
    }
}