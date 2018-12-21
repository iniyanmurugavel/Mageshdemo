package com.example.bcs.visualstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RangesForAxis implements View.OnFocusChangeListener {
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



    public RangesForAxis(Context context, TextView values, int string, EditText edit1, EditText edit3, Double min, Double max, TextView errorView, int msg) {
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


                    int i = Integer.valueOf(edit1.getText().toString());

                    if (i > max) {

                        edit1.setBackground(context.getResources().getDrawable(R.drawable.error_background));
                        errorView.setText(msg);

                    } else if (i < min) {

                        edit1.setBackground(context.getResources().getDrawable(R.drawable.error_background));
                        errorView.setText(msg);
                    } else {
                        errorView.setText("");
                        edit3.setText(edit1.getText().toString().trim());

                        edit1.setBackground(context.getResources().getDrawable(R.drawable.order_reference_button));
                    }

                    // Return true if you have consumed the action, else false.

                }
            }
        }
    }
}