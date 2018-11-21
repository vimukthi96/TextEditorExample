package com.example.vimukthi.texteditorexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class FindTextDialog {

    MultiAutoCompleteTextView edtTextView;
    Context context;
    String subString;

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
    /*    if(!isHighlight) {
            textHighlighter= new TextHighlighter();
            textHighlighter
                    .setBackgroundColor(Color.parseColor("FFFF00"))
                    .setForegroundColor(Color.RED)
                    .addTarget(findViewById(R.id.edtTextView))
                    .highlight("html", TextHighlighter.BASE_MATCHER);

        }
        else{
            textHighlighter.setBackgroundColor(Color.TRANSPARENT)
                    .setForegroundColor(edtTextView.getCurrentTextColor())
                    .invalidate( TextHighlighter.BASE_MATCHER);
        }
        isHighlight =! isHighlight;*/
       /* String cv=edtTextView.getText().toString();
        SpannableString ss=new SpannableString(cv);
        BackgroundColorSpan backgroundColorSpan=new BackgroundColorSpan(Color.YELLOW);
        ss.setSpan(backgroundColorSpan,0,5,SPAN_EXCLUSIVE_EXCLUSIVE);
        edtTextView.setText(ss);*/
        String mainString = edtTextView.getText().toString();
     //   subString = text;


      /*  if(mainString.contains(subString)) {
            int startIndex = mainString.indexOf(subString);
            int endIndex = startIndex + subString.length();
            SpannableString spannableString = new SpannableString(mainString);
            spannableString.setSpan(new BackgroundColorSpan(Color.parseColor("#ffff00")), startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            edtTextView.setText(spannableString);
        }
   /*     String adam = "<font color=#FFFF00>"+subString+"</font>";
      //  String str = "Na Adam ne ne yere Hawa: Na Adam xwoo xbabarima";
        String newString = mainString.replaceAll(subString, adam);
        edtTextView.setText(Html.fromHtml(newString));*/
        //   String str = "Na Adam ne ne yere Hawa: Na Adam xwoo xbabarima";
        //    String stringToColor = "Adam";
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

        return true;
    }



}
