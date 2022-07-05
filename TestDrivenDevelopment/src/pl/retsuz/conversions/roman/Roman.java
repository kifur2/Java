package pl.retsuz.conversions.roman;

import pl.retsuz.conversions.GenericNumeralSystem;

import java.util.HashMap;
import java.util.Map;

public class Roman implements GenericNumeralSystem {

    private final Map<Character, Integer> toArabicMap = new HashMap<Character, Integer>();
    private final Map<String, Integer> fromArabicMap = new HashMap<String, Integer>();

    private final String roman[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX","V", "IV", "I"};

    public Roman() {
        toArabicMap.put('I', 1);
        toArabicMap.put('V', 5);
        toArabicMap.put('X', 10);
        toArabicMap.put('L', 50);
        toArabicMap.put('C', 100);
        toArabicMap.put('D', 500);
        toArabicMap.put('M', 1000);

        fromArabicMap.put("M", 1000);
        fromArabicMap.put("CM", 900);
        fromArabicMap.put("D", 500);
        fromArabicMap.put("CD", 400);
        fromArabicMap.put("C", 100);
        fromArabicMap.put("XC", 90);
        fromArabicMap.put("L", 50);
        fromArabicMap.put("XL", 40);
        fromArabicMap.put("X", 10);
        fromArabicMap.put("IX", 9);
        fromArabicMap.put("V", 5);
        fromArabicMap.put("IV", 4);
        fromArabicMap.put("I", 1);

    }

    @Override
    public String fromArabic(int val) {
        String wynik = "";
        if (val < 1 || val > 3000)
            throw new IllegalArgumentException();

        while(val>0){
            for(String i : roman){
                while(val >= fromArabicMap.get(i)){
                    val-=fromArabicMap.get(i);
                    wynik+= i;
                }
            }
        }
        return wynik;
        //throw new UnsupportedOperationException();
    }

    @Override
    public int toArabic(String val) {
        int wynik = 0;
        int licznik = 1;

        for (int i = 0; i < val.length(); i++) {
            if (toArabicMap.containsKey(val.charAt(i))) {
                if (i + 1 < val.length() && toArabicMap.containsKey(val.charAt(i + 1))) {
                    if (toArabicMap.get(val.charAt(i)) < toArabicMap.get(val.charAt(i + 1))) {
                        wynik -= toArabicMap.get(val.charAt(i));
                        licznik = 1;
                    }
                    else {
                        if (toArabicMap.get(val.charAt(i)) == toArabicMap.get(val.charAt(i + 1))){
                            licznik++;
                            if(toArabicMap.get(val.charAt(i)) == 5 || toArabicMap.get(val.charAt(i)) == 50 || toArabicMap.get(val.charAt(i)) == 500 || licznik == 4)
                                throw new IllegalArgumentException();
                        }
                        else
                            licznik = 1;
                        wynik += toArabicMap.get(val.charAt(i));
                    }
                } else wynik += toArabicMap.get(val.charAt(i));
            } else throw new UnsupportedOperationException();
        }

        if (wynik < 1 || wynik > 3000)
            throw new IllegalArgumentException();
        return wynik;
        // throw new UnsupportedOperationException();
    }
}
