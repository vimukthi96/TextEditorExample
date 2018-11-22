package com.example.vimukthi.texteditorexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindTextDialog {

    MultiAutoCompleteTextView edtTextView;
    Context context;
    String subString;
    SpannableString ss;
    Matcher matcher;

    public FindTextDialog(Context context1) {
        this.context =context1;
    }



    public boolean showFindDialog(MultiAutoCompleteTextView editTextView) {

        edtTextView =editTextView;
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Find");


        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View layout_find = layoutInflater.inflate(R.layout.dialog_find_text, null);

        final MaterialEditText edtTextSearch = layout_find.findViewById(R.id.txt_search);
        final MaterialEditText edtTextReplace = layout_find.findViewById(R.id.txt_replace);
        edtTextReplace.setVisibility(View.GONE);
        final CheckBox checkBox=layout_find.findViewById(R.id.check_replace);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTextReplace.setVisibility(View.VISIBLE);
            }
        });


        alertDialog.setView(layout_find);

        alertDialog.setPositiveButton("Find", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


                if (TextUtils.isEmpty(edtTextSearch.getText())) {
                     Toast.makeText(context,"TExt field is empty", Toast.LENGTH_SHORT).show();
                   // Snackbar.make(relativeLayout,"find text field is empty or space",Snackbar.LENGTH_SHORT).show();
                    return;
                }

              //  textHihjlight(edtTextSearch.getText().toString());
                subString = edtTextSearch.getText().toString();
                textHihjlight();
            }
        });
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();

    return true;
    }

    public boolean textHihjlight() {


   /*     String mainString = edtTextView.getText().toString();

        int ofe = mainString.indexOf(subString, 0);
        Spannable WordtoSpan = new SpannableString(mainString);

        for (int ofs = 0; ofs < mainString.length() && ofe != -1; ofs = ofe + 1) {


            ofe = mainString.indexOf(subString, ofs);
            if (ofe == -1)
                break;
            else {
                WordtoSpan.setSpan(new BackgroundColorSpan(Color.YELLOW), ofe, ofe + subString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                edtTextView.setText(WordtoSpan, TextView.BufferType.SPANNABLE);
            }

}
        }*/
Editable editable = edtTextView.getEditableText();
            String text=edtTextView.getText().toString();
            ss =new SpannableString(text);
            String textToSearch=subString;
            Pattern pattern=Pattern.compile(textToSearch);
            matcher=pattern.matcher(ss);
            while (matcher.find())
            {
                Editable ab = new SpannableStringBuilder(editable.toString().replace("g", "G"));
                editable.replace(0, edtTextView.length(), ab);
            }
           //   ss.setSpan(new replace("gh","ghyu"),matcher.start(),matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE      );
                //  ss.setSpan(new ForegroundColorSpan(Color.RED),matcher.start(),matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
           // }
              //  edtTextView.setText(ss);
                ss=null;


        return true;
    }



}
