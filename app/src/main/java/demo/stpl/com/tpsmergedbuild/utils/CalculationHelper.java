package demo.stpl.com.tpsmergedbuild.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Created by stpl on 9/9/2016.
 */
public class CalculationHelper {

    public static ArrayList<Integer> quickPickNumberGenerator(int num, int maxNum) {
        ArrayList<Integer> numbers = new ArrayList<>();
        int count = 0;
        do {
            int number = new Random().nextInt(maxNum);
            if (numbers.size() == 0 || !numbers.contains(number)) {
                numbers.add(number);
                ++count;
            }
        } while (count < num);
        return numbers;
    }

    public static String amountFormat(double amt) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        format.applyPattern("###0.00");
        return format.format(amt);
    }

    public static String amountFormatOneDigit(double amt) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        format.applyPattern("###0.0");
        return format.format(amt);
    }

    public static double amountFormatOneDigitDouble(double amt) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        format.applyPattern("###0.0");
        return Double.parseDouble(format.format(amt));
    }

    public static long combination(int n, int r) {
        return fact(n) / (fact(r) * fact(n - r));
    }

    public static long fact(long n) {
        if (n == 0)
            return 1;
        else
            return n * fact(n - 1);
    }
}
