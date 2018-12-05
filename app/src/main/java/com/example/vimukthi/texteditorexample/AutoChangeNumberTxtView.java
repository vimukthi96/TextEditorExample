package com.example.vimukthi.texteditorexample;

import android.content.Context;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class AutoChangeNumberTxtView {
    Context context;
    EditText edtTextView;
    TextView txtnumberView;

    public AutoChangeNumberTxtView(Context context,EditText editTextView, TextView txtNumberView) {
        this.context = context;
        edtTextView = editTextView;
        txtnumberView = txtNumberView;
    }

    public void autoOnchange() {
        int lines = edtTextView.getLineCount();
        String lineText = "";
        for (int r = 1; r <= lines; r++) {
            lineText = lineText + r + "\n";

        }
        txtnumberView.setText(lineText);
        // abc();
    }
}
