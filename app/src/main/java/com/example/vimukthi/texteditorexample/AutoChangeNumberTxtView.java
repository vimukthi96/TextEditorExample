package com.example.vimukthi.texteditorexample;

import android.content.Context;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class AutoChangeNumberTxtView {
    Context context;
    MultiAutoCompleteTextView edtTextView;
    TextView txtnumberView;

    public AutoChangeNumberTxtView(Context context,MultiAutoCompleteTextView editTextView, TextView txtNumberView) {
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
