package com.example.bcs.visualstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class RangesDisplay implements View.OnFocusChangeListener {


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

    public RangesDisplay(TextView values, int string, Context context, EditText edit1, EditText edit3, Double min, Double max, TextView errorView,
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

    public RangesDisplay(Context context, TextView values, int string, EditText edit1, EditText edit3, Double min, Double max, TextView errorView, int msg) {
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
//                    edit3.setText(edit1.getText().toString().trim());
                        @SuppressLint("DefaultLocale") String format = String.format("%.2f", i);


                        if(edit1.getText().toString().matches("[0-9]+.0[0-9][0-9]*")){
                            String rep=edit1.getText().toString();
                            System.out.println("Matches[0]" + rep);
                            edit1.setText(rep.replaceAll("0[0-9][0-9]*", "00"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.1[1-2][0-9]*")){
                            String rep=edit1.getText().toString();
                            System.out.println("Matches[1]" + rep);
                            edit1.setText(rep.replaceAll("1[0-2][0-9]*", "00"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.1")){
                            String rep=edit1.getText().toString();
                            System.out.println("Matches[1.1]" + rep);
                            edit1.setText(rep.replace(".1", ".00"));
                            edit3.setText(edit1.getText().toString());
                        }  else if(edit1.getText().toString().matches("[0-9]+.1[3-9][0-9]*")){
                            String rep=edit1.getText().toString();
                            System.out.println("Matches[2]" + rep);
                            edit1.setText(rep.replaceAll(".1[3-9][0-9]*", ".25"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.2")){
                            String rep=edit1.getText().toString();
                            System.out.println("Matches[2.1]" + rep);
                            edit1.setText(rep.replace(".2",".25"));
                            edit3.setText(edit1.getText().toString());
                        }  else if(edit1.getText().toString().matches("[0-9]+.2[0-9][0-9]*")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[3]" + rep);
                            edit1.setText(rep.replaceAll(".2[0-9][0-9]*", ".25"));
                            edit3.setText(edit1.getText().toString());
                        }  else if(edit1.getText().toString().matches("[0-9]+.2[0-9][0-9]*")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[3.01]" + rep);
                            edit1.setText(rep.replace(".2", ".25"));
                            edit3.setText(edit1.getText().toString());
                        }   else if(edit1.getText().toString().matches("[0-9]+.3")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[3.02]" + rep);
                            edit1.setText(rep.replaceAll(".3", ".25"));
                            edit3.setText(edit1.getText().toString());
                        }   else if(edit1.getText().toString().matches("[0-9]+.3[0-9][0-9]*")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[3.03]" + rep);
                            edit1.setText(rep.replaceAll(".3[0-9][0-9]*", ".25"));
                            edit3.setText(edit1.getText().toString());
                        } else if(edit1.getText().toString().matches("[0-9]+.4")) {
                        String rep = edit1.getText().toString();
                        System.out.println("Matches[3.1]" + rep);
                        edit1.setText(rep.replace(".4", ".50"));
                        edit3.setText(edit1.getText().toString());
                    } else if(edit1.getText().toString().matches("[0-9]+.[4-5][0-9][0-9]*")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[4]" + rep);
                            edit1.setText(rep.replaceAll("[4-5][0-9][0-9]*", "50"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.6[0-2][0-9]*")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[4.1]" + rep);
                            edit1.setText(rep.replaceAll(".6[0-2][0-9]*", ".50"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.6")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[4.2]" + rep);
                            edit1.setText(rep.replace(".6", ".50"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.6[2-9][0-9]*")) {
                        String rep = edit1.getText().toString();
                        System.out.println("Matches[5]" + rep);
                        edit1.setText(rep.replaceAll(".6[2-9][0-9]*", ".75"));
                        edit3.setText(edit1.getText().toString());
                    }else if(edit1.getText().toString().matches("[0-9]+.7")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[6]" + rep);
                            edit1.setText(rep.replace(".7", ".75"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.7[0-9][0-9]*")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[6.1]" + rep);
                            edit1.setText(rep.replaceAll(".7[0-9][0-9]*", ".75"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.8[0-7][0-9]*")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[7]" + rep);
                            edit1.setText(rep.replaceAll(".8[0-7][0-9]*", ".75"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.8")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[7.1]" + rep);
                            edit1.setText(rep.replaceAll(".8", ".75"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.8[8-9][0-9]*")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[8]" + rep);
                            edit1.setText(rep.replaceAll(".8[8-9][0-9]*", ".00"));
                            edit3.setText(edit1.getText().toString());
                        }else if(edit1.getText().toString().matches("[0-9]+.9[0-9][0-9]*")) {
                            String rep = edit1.getText().toString();
                            System.out.println("Matches[8]" + rep);
                            edit1.setText(rep.replaceAll(".9[0-9][0-9]*", ".00"));
                            edit3.setText(edit1.getText().toString());
                        }else {
                            edit3.setText(format);
                            edit1.setText(format);
                        }

                        System.out.println("value with decimal" + String.format("%.2f", i));
                        edit1.setBackground(context.getResources().getDrawable(R.drawable.order_reference_button));
                    }

                    // Return true if you have consumed the action, else false.

                }
            }
        }
    }
}
