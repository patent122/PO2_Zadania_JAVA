public class Zadanie_1 {

    public static void main(String[] args) {
	    try {
            double a = Double.parseDouble(args[0]);
            double b = Double.parseDouble(args[1]);
            double c = Double.parseDouble(args[2]);
            double delta = b * b - 4 * a * c;

            if (delta >= 0){
                double x1 = (-b - Math.sqrt(delta)) / 2.0 * a;
                double x2 = (-b + Math.sqrt(delta)) / 2.0 * a;
                System.out.println("x1 = " + x1);
                System.out.println("x2 = " + x2);
            }else{
                System.out.println("Brak rozwiązania w dziedzinie liczb rzeczywistych");
            }

        }
        catch (NumberFormatException e) {
            System.out.println("Podano liczbę w nieprawidłowym formacie");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Podano za mało argumentów");
        }
    }
}
