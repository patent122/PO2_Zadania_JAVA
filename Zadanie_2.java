import java.sql.SQLOutput;

public class Zadanie_2 {
    public static void main(String[] args) {
        try {
            String tekst = args[0];
            int a = Integer.parseInt(args[1]);
            int b = Integer.parseInt(args[2]);

            String wycinek = tekst.substring(a, b + 1);

            System.out.println(wycinek);


        } catch (NumberFormatException e) {
            System.out.println("Podano liczbę w nieprawidłowym formacie");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Podano za mało argumentów");
        }
        catch (StringIndexOutOfBoundsException e){
            System.out.println("Podane indeksy mają nieprawidłowy zakres");
        }
    }
}
