package pl.retsuz;

import pl.retsuz.conversions.Converter;
import pl.retsuz.conversions.GenericNumeralSystem;
import pl.retsuz.conversions.arabic.Arabic;
import pl.retsuz.conversions.roman.Roman;
import pl.retsuz.conversions.urnfield.Urnfield;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final GenericNumeralSystem[] ar = { new Arabic(), new Roman(), new Urnfield()};

    protected static int parseInteger(String message, int min, int max){
        System.out.printf("%s", message);
        String line;
        int res;
        try{
            line = sc.nextLine();
            res = Integer.parseInt(line);
            if(res < min || res > max)
                throw new IllegalArgumentException();
        }catch (Exception ex){
            System.err.println("Wprowadzono niepoprawne dane!");
            res  = parseInteger(message, min, max);
        }
        return res;
    }

    public static void main(String[] args) {
        boolean zm = true;
        while(zm){
            System.out.println("------ Menu ------");
            System.out.println("0 - Wyjscie");
            System.out.println("1 - Konwersja");
            System.out.printf("wybierz opcje: ");
            int x = sc.nextInt();
            switch (x){
                case 0:
                    zm = false;
                    break;
                case 1:
                    System.out.println("------ Dostepne Systemy: ------");
                    System.out.println("0 - System Arabski");
                    System.out.println("1 - System Rzymski");
                    System.out.println("2 - System Prastary");
                    sc.nextLine();
                    GenericNumeralSystem from = ar[parseInteger("Wybierz system z ktorego chcesz zrobic konwersje liczby: ",0,2)];
                    GenericNumeralSystem to = ar[parseInteger("Wybierz system do ktorego chcesz zrobic konwersje liczby: ", 0, 2)];
                    Converter converter = new Converter();
                    System.out.printf("Podaj liczbe do konwersji: ");
                    String value = sc.nextLine();
                    System.out.printf("Po konwersji: ");
                    String result = "";
                    try {
                        result = converter.convert(from, to, value);
                    }catch (Exception ex){
                        System.out.println(ex);
                    }
                    System.out.println(result);
                    break;
                default:
                    System.out.println("Sprobuj ponownie");
            }

        }
    }
}
