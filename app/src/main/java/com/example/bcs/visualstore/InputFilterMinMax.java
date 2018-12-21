package com.example.bcs.visualstore;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMinMax implements InputFilter {
    private double min, max;

    public InputFilterMinMax(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public InputFilterMinMax(String min, String max) {
        this.min = Double.parseDouble(min);
        this.max = Double.parseDouble(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String stringInput = dest.toString() + source.toString();

            double value;
            if (stringInput.length() == 1 && stringInput.charAt(0) == '-') {

                value = -1;
            }else  {

                value = Double.parseDouble(stringInput);

            }
          //  Double value = Double.parseDouble(dest.toString() + source.toString());
            if (isInRange(min, max, value))
                return null;
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

        private boolean isInRange(double a, double b, double c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
}

