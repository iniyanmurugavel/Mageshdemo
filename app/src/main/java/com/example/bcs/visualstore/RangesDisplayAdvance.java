package com.example.bcs.visualstore;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class RangesDisplayAdvance implements View.OnFocusChangeListener {



    private TextView values;
    private int string;



    public RangesDisplayAdvance( TextView values, int string) {
        this.values = values;
        this.string = string;

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(b){
            values.setText(string);

        }else {
            values.setText("");




        }
    }
}