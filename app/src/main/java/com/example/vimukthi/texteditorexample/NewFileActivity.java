package com.example.vimukthi.texteditorexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.xeoh.android.texthighlighter.TextHighlighter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import dmax.dialog.SpotsDialog;


public class NewFileActivity extends AppCompatActivity {

    public TextView txtnumberView;
    public MultiAutoCompleteTextView  edtTextView;
    RelativeLayout relativeLayout;
    ArrayAdapter<String> adapter;
    TextViewUndoRedo helper;
    Menu menu;
    MenuItem undo_btn;
    //boolean b = false;
    MenuItem redo_btn;
    FileSaveDialog fileSaveDialog;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_file);

        txtnumberView = (TextView) findViewById(R.id.numberViewText);
        edtTextView = (MultiAutoCompleteTextView ) findViewById(R.id.edtTextView);
        relativeLayout=(RelativeLayout)findViewById(R.id.layout_root);
        helper = new TextViewUndoRedo(edtTextView);
        context=NewFileActivity.this;
        fileSaveDialog = new FileSaveDialog(context);
        edtTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int lines = edtTextView.getLineCount();
                String lineText = "";
                for (int r = 1; r <= lines; r++) {
                    lineText = lineText + r + "\n";

                }
                txtnumberView.setText(lineText);
                String[] html = getResources().getStringArray(R.array.html);
                adapter = new ArrayAdapter<String>(NewFileActivity.this, android.R.layout.simple_list_item_1, html);
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
                textInType();


            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



}
            public boolean textInType() {
                if (edtTextView.getText().length() == 0) {
                   // undoBtn.setEnabled(false);
                    undo_btn.setVisible(false);
                    redo_btn.setVisible(false);
                    return false;
                } else {
                 //   undoBtn.setEnabled(true);
                  //  b=true;
                    undo_btn.setVisible(true);
                    return true;

                }
            }


            private void performRedo() {
                helper.redo();
            }

            private void performUndo() {
                helper.undo();
            }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        //   undo_btn.setEnabled(b);
        undo_btn.setVisible(false);
        redo_btn.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_file_view, menu);
        undo_btn = (MenuItem) menu.findItem(R.id.action_undo_btn);
        redo_btn = (MenuItem) menu.findItem(R.id.action_redo_btn);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_as_btn:
             //   Toast.makeText(this, "hehdejd jehfgehkd", Toast.LENGTH_SHORT).show();
                //saveAsDialog();
                if(fileSaveDialog.saveAsDialog(edtTextView)==true){
                    item.setVisible(false);
                }

                return true;
            case R.id.action_undo_btn:
                if (textInType() == true) {

                    performUndo();
                }
                redo_btn.setVisible(true);
                return true;

            case R.id.action_redo_btn:
                performRedo();
                return true;

            case R.id.action_find_btn:
                showFindDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFindDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Find");


        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View layout_find = layoutInflater.inflate(R.layout.dialog_find_text, null);

        final MaterialEditText edtTextSearch = layout_find.findViewById(R.id.txt_search);

        alertDialog.setView(layout_find);

        alertDialog.setPositiveButton("Find", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


                if (TextUtils.isEmpty(edtTextSearch.getText())) {
                    // Toast.makeText(NewFileActivity.this,"TExt field is empty", Toast.LENGTH_SHORT).show();
                    Snackbar.make(relativeLayout,"find text field is empty or space",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // final SpotsDialog waitingDialog=new SpotsDialog(NewFileActivity.this);
                // waitingDialog.show();

                textHihjlight(edtTextSearch.getText().toString());
            }
        });
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();


    }


  /*  private void saveDialog(String path,String name) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Save");


        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View layout_save_as = layoutInflater.inflate(R.layout.dialog_save_as, null);


  //      final MaterialEditText edtTextName = layout_save_as.findViewById(R.id.dialog_name);
      //  final MaterialEditText edtFolderPath = layout_save_as.findViewById(R.id.dialog_path);
        edtTextName.setText();
        alertDialog.setView(layout_save_as);
        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
        String fileBody=edtTextView.getText().toString();
            String filePath=path;
            String fileName=name;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                createPDF( fileName, filePath, fileBody);

            }
        });
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();


    }*/

 /* boolean isHighlight = false;
    TextHighlighter textHighlighter;*/

    private void textHihjlight(String text) {
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
        String subString = text;


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
    }



}



