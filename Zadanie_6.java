import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadanie_6 {
    public static void main(String[] args) throws Exception {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        Scanner skaner = new Scanner(System.in);

        while (true) {
            System.out.println("Podaj wektor A: ");
            czytajWektor(skaner, a);
            System.out.println("Podaj wektor B: ");
            czytajWektor(skaner, b);
            try {
                List<Integer> c = dodajWektory(a, b);
                System.out.println(c);
                zapiszWektor("c.txt", c);
            } catch (WektoryRóżnejDługościException e) {
                System.out.println("\033[38;2;200;11;11m" + e.getMessage() + "\033[0m");
                a.clear();
                b.clear();
                continue;

            }
            break;
        }

    }
    private static void czytajWektor(Scanner skaner, List<Integer> wektor) {
        String linia = skaner.nextLine();
        String[] tokeny = linia.split(" ");
        for (String token : tokeny) {
            try {
                wektor.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                continue;
            }
        }

    }

    private static List<Integer> dodajWektory (List<Integer> a, List<Integer> b) throws WektoryRóżnejDługościException {
        if (a.size() != b.size()) {
            throw new WektoryRóżnejDługościException(a.size(), b.size());

            }
            List<Integer> wynik = new ArrayList<>();
            for (int i = 0; i < a.size(); i++) {
                wynik.add(a.get(i) + b.get(i));
            }
            return wynik;
        }


    private static void zapiszWektor (String ścieżka, List<Integer> wektor) throws Exception {
        Files.writeString(Paths.get(ścieżka), wektor + "\n", StandardOpenOption.APPEND);
    }
}

class WektoryRóżnejDługościException extends Exception {
    public WektoryRóżnejDługościException(int długośćA, int długośćB) {
        super(String.format("Długość wektora A to %d, a wektora B to %d", długośćA, długośćB));
    }
}
