package me.marcusslover.caverns.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberUtil {
    public static String toFancyNumber(int num) {
        return NumberFormat.getInstance(Locale.US).format((Integer) num);
    }

    public static String toFancyNumber(double num) {
        return NumberFormat.getInstance(Locale.US).format(num);
    }

    public static Integer fromFancyNumber(String num) {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number = 0;
        try {
            number = format.parse(num);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number.intValue();
    }

    public static double fromFancyDouble(String num) {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number = 0.0D;
        try {
            number = format.parse(num);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number.doubleValue();
    }

    public static String toRoman(int input) {
        if (input < 1 || input > 3999)
            return "Invalid Roman Number Value";
        StringBuilder s = new StringBuilder();
        while (input >= 1000) {
            s.append("M");
            input -= 1000;
        }
        while (input >= 900) {
            s.append("CM");
            input -= 900;
        }
        while (input >= 500) {
            s.append("D");
            input -= 500;
        }
        while (input >= 400) {
            s.append("CD");
            input -= 400;
        }
        while (input >= 100) {
            s.append("C");
            input -= 100;
        }
        while (input >= 90) {
            s.append("XC");
            input -= 90;
        }
        while (input >= 50) {
            s.append("L");
            input -= 50;
        }
        while (input >= 40) {
            s.append("XL");
            input -= 40;
        }
        while (input >= 10) {
            s.append("X");
            input -= 10;
        }
        while (input >= 9) {
            s.append("IX");
            input -= 9;
        }
        while (input >= 5) {
            s.append("V");
            input -= 5;
        }
        while (input >= 4) {
            s.append("IV");
            input -= 4;
        }
        while (input >= 1) {
            s.append("I");
            input -= 1;
        }
        return s.toString();
    }
}
