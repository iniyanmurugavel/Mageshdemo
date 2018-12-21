package com.example.bcs.visualstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RangesDisplay1 implements View.OnFocusChangeListener {
    private TextView values;
    private int string;
    private Context context;
    private EditText edit1;
    private EditText edit3;
    private Double min;
    private Double max;
    private TextView errorView;
    private int msg;
    private EditText cyR;
    private EditText axisR;
    private EditText additionR;
    private EditText cyL;
    private EditText axisL;
    private EditText additionL;

    public RangesDisplay1(TextView values, int string, Context context, EditText edit1, EditText edit3, Double min, Double max, TextView errorView,
                          int msg, EditText cyR, EditText axisR, EditText additionR, EditText cyL, EditText axisL, EditText additionL) {
        this.values = values;
        this.string = string;
        this.context = context;
        this.edit1 = edit1;
        this.edit3 = edit3;
        this.min = min;
        this.max = max;
        this.errorView = errorView;
        this.msg = msg;
        this.cyR = cyR;
        this.axisR = axisR;
        this.additionR = additionR;
        this.cyL = cyL;
        this.axisL = axisL;
        this.additionL = additionL;
    }

    public RangesDisplay1(Context context, TextView values, int string, EditText edit1, EditText edit3, Double min, Double max, TextView errorView, int msg) {
        this.values = values;
        this.string = string;
        this.context = context;
        this.edit1 = edit1;

        this.edit3 = edit3;
        this.min = min;
        this.max = max;
        this.errorView = errorView;
        this.msg = msg;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            values.setText(string);

        } else {
            values.setText("");

            if (edit1.getText().toString().matches("[-]?[0-9]*\\.?[0-9]*")) {
                if (!edit1.getText().toString().isEmpty()) {


                    Double i = Double.valueOf(edit1.getText().toString());

                    if (i > max) {

                        edit1.setBackground(context.getResources().getDrawable(R.drawable.error_background));
                        errorView.setText(msg);

                    } else if (i < min) {

                        edit1.setBackground(context.getResources().getDrawable(R.drawable.error_background));
                        errorView.setText(msg);
                    } else {
                        errorView.setText("");
                        edit3.setText(edit1.getText().toString().trim());
                    @SuppressLint("DefaultLocale") String format=String.format("%.2f", i);
                    edit3.setText(format);
                    edit1.setText(format);
                    System.out.println("value with decimal"+String.format("%.2f", i));
                        edit1.setBackground(context.getResources().getDrawable(R.drawable.order_reference_button));
                    }

                    // Return true if you have consumed the action, else false.

                }
            }
        }
    }
}