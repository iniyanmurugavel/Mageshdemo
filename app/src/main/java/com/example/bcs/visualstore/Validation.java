package com.example.bcs.visualstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class Validation implements TextView.OnEditorActionListener {


    private Context context;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private Double min;
    private Double max;
    private TextView errorView;
    private int msg;

    //   final EditText edit2, final EditText axis1, final EditText addition1,
//    final EditText edit3, final EditText cylind2, final EditText axis2, final EditText addition2
    public Validation(Context context, EditText edit1, EditText edit2, EditText edit3,Double min,Double  max,TextView errorView,int msg) {

        this.context = context;
        this.edit1 = edit1;
        this.edit2 = edit2;
        this.edit3 = edit3;
        this.min=min;
        this.max=max;
        this.errorView=errorView;
        this.msg=msg;
        }

    @SuppressLint("DefaultLocale")
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if ( !edit1.getText().toString().isEmpty()  ) {


            Double i = Double.valueOf(edit1.getText().toString());

            if (i > max) {

                edit1.setBackground(context.getResources().getDrawable(R.drawable.error_background));
                errorView.setText(msg);

            } else if (i < min) {

                edit1.setBackground(context.getResources().getDrawable(R.drawable.error_background));
                errorView.setText(msg);
            } else {

                edit2.requestFocus();
                errorView.setText("");
//                String format=String.format("%.2f", i);
//                edit3.setText(format);
//                edit1.setText(format);
//                System.out.println("value with decimal"+String.format("%.2f", i));
                edit1.setBackground(context.getResources().getDrawable(R.drawable.order_reference_button));
            }


            return true;


        }
        // Return true if you have consumed the action, else false.
        return false;
    }
}




