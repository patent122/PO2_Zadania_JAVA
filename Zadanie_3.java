import java.util.Random;
import java.util.Scanner;

public class Zadanie_3 {
    public static void main(String[] args) {
        try {
            int n = Integer.parseInt(args[0]);

            if (n >= 10) {
                Scanner skaner = new Scanner(System.in);
                while (true) {
                    graj(n, skaner);

                    System.out.println("Czy chcesz grać od nowa? (t/n)");

                    if (!skaner.next().equals("t")) {
                        break;
                    }
                }


            } else {
                System.out.println("N powinno być większe lub równe 10");
            }


            } catch(NumberFormatException e){
                System.out.println("Podano liczbę w nieprawidłowym formacie");
            }
        catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Podano za mało argumentów");
            }
        catch(StringIndexOutOfBoundsException e){
                System.out.println("Podane indeksy mają nieprawidłowy zakres");
            }
        }
        private static void graj(int n, Scanner skaner){
            Random random = new Random();
            int wylosowana = random.nextInt(n + 1);
            int próby = 0;

            while (true) {
                System.out.println("Zgadnij liczbę: ");
                int odgadywana= skaner.nextInt();

                próby++;

                if (odgadywana < wylosowana){
                    System.out.println("Za mało");
                } else if (odgadywana > wylosowana){
                    System.out.println("Za dużo");
                } else {
                    System.out.println("Odgatnięto w " + próby + " prób");
                    break;
                }
            }
        }
    }


