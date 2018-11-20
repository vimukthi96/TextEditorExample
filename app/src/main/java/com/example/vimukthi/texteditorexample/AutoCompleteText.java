package com.example.vimukthi.texteditorexample;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.ResourceBundle;

public class AutoCompleteText {
    Context context;
    MultiAutoCompleteTextView edtTextView;
    TextView txtnumberView;
    ArrayAdapter<String> adapter;
    ListView listView ;
    public AutoCompleteText(Context context) {
        this.context = context;
    }

    public void autoOnchange(MultiAutoCompleteTextView editTextView,TextView txtNumberView){

        edtTextView =editTextView;
        txtnumberView =txtNumberView;

        int lines = edtTextView.getLineCount();
        String lineText = "";
        for (int r = 1; r <= lines; r++) {
            lineText = lineText + r + "\n";

        }
        txtnumberView.setText(lineText);
        String[] html = context.getResources().getStringArray(R.array.html);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_activated_1, html);
        edtTextView.setAdapter(adapter);
        edtTextView.setThreshold(2);
        edtTextView.setTokenizer(new MultiAutoCompleteTextView.Tokenizer() {
            @Override
            public int findTokenStart(CharSequence charSequence, int cursor) {
                int i = cursor;

                while (i > 0 && charSequence.charAt(i - 1) != '<') {
                    i--;
                }
                while (i < cursor && charSequence.charAt(i) == '<') {
                    i++;
                }

                return i;
            }

            @Override
            public int findTokenEnd(CharSequence charSequence, int cursor) {
                int i = cursor;
                int len = charSequence.length();

                while (i < len) {
                    if (charSequence.charAt(i) == ' ') {
                        return i;
                    } else {
                        i++;
                    }
                }

                return len;
            }

            @Override
            public CharSequence terminateToken(CharSequence charSequence) {
                int i = charSequence.length();

                while (i > 0 && charSequence.charAt(i - 1) == ' ') {
                    i--;
                }

                if (i > 0 && charSequence.charAt(i - 1) == ' ') {
                    return charSequence;
                } else {
                    if (charSequence instanceof Spanned) {
                        SpannableString sp = new SpannableString(charSequence + " ");
                        TextUtils.copySpansFrom((Spanned) charSequence, 0, charSequence.length(),
                                Object.class, sp, 0);
                        return sp;
                    } else {
                        return charSequence + ">" + "</" + charSequence + ">";
                    }
                }
            }
        });
    }


}
