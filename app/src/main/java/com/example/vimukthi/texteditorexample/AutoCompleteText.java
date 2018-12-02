package com.example.vimukthi.texteditorexample;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.ResourceBundle;

public class AutoCompleteText {
    Context context;
    MultiAutoCompleteTextView edtTextView;
    ArrayAdapter<String> adapter;
    ListView listView ;
    String[] dataType;

    public AutoCompleteText(Context context,MultiAutoCompleteTextView edtTextView) {
        this.context = context;
        this.edtTextView=edtTextView;
    }


    public void  autoComplete() {
        switch (Common.getCurrentExtention()){
            case "html":
                dataType = context.getResources().getStringArray(R.array.html);
                break;
            case "txt":
                dataType = context.getResources().getStringArray(R.array.txt);
                break;
            case "js":
                dataType = context.getResources().getStringArray(R.array.txt);
                break;
            case "css":
                dataType = context.getResources().getStringArray(R.array.txt);
                break;


        }
            adapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, dataType);


            edtTextView.setAdapter(adapter);
            edtTextView.setThreshold(2);
           // edtTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

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
                            SpannableString sp = new SpannableString(charSequence + "");
                            TextUtils.copySpansFrom((Spanned) charSequence, 0, charSequence.length(),
                                    Object.class, sp, 0);
                            return sp;
                        }
                        else {
                            return charSequence + ">" + "</" + charSequence + ">";
                        }
                    }

                }
            });
        }
    }





