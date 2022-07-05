package pl.retsuz.conversions.urnfield;

import pl.retsuz.conversions.GenericNumeralSystem;

public class Urnfield implements GenericNumeralSystem {
    @Override
    public String fromArabic(int val) {
        if (val > 29 || val < 1)
            throw new IllegalArgumentException();
        String wynik = "";
        while (val % 5 != 0) {
            wynik += "/";
            val -= 1;
        }
        while (val > 4) {
            wynik += "\\";
            val -= 5;
        }
        return wynik;
    }

    @Override
    public int toArabic(String val) {
        int wynik = 0;
        for (char i : val.toCharArray()) {
            if (i == '\\')
                wynik += 5;
            else if (i == '/') {
                wynik += 1;
            } else throw new UnsupportedOperationException();
        }
        if (wynik > 29 || wynik < 1)
            throw new IllegalArgumentException();
        return wynik;
    }
}
