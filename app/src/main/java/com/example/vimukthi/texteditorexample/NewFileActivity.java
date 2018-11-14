package com.example.vimukthi.texteditorexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class NewFileActivity extends AppCompatActivity {

    public TextView txtnumberView;
    public EditText edtTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_file);

        txtnumberView=(TextView)findViewById(R.id.numberViewText);
        edtTextView=(EditText)findViewById(R.id.edtTextView);

        edtTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int lines=edtTextView.getLineCount();
                String lineText="";
                for(int r = 1 ; r<=lines; r++) {
                    lineText =lineText+ r+"\n";
                    
                }
                txtnumberView.setText(lineText);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
