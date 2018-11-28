package com.example.vimukthi.texteditorexample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewFileActivity extends AppCompatActivity {

    public TextView txtnumberView;
    public MultiAutoCompleteTextView  edtTextView;
    RelativeLayout relativeLayout;
    TextViewUndoRedo helper;
    Menu menu;
    MenuItem undo_btn;
    MenuItem redo_btn;
    FileSaveDialog fileSaveDialog;
    FindTextDialog findTextDialog;
    AutoCompleteText autoCompleteText;
    AutoChangeNumberTxtView autoChangeNumberTxtView;
    Context context;
    MenuItem save_btn;
    String[] dataType;
    StringBuilder regex;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_file);

        txtnumberView = (TextView) findViewById(R.id.numberViewText);
        edtTextView = (MultiAutoCompleteTextView) findViewById(R.id.edtTextView);
        relativeLayout = (RelativeLayout) findViewById(R.id.layout_root);
        helper = new TextViewUndoRedo(edtTextView);
        context = NewFileActivity.this;
        fileSaveDialog = new FileSaveDialog(context);
        findTextDialog = new FindTextDialog(context);
        autoCompleteText = new AutoCompleteText(context);
        autoChangeNumberTxtView=new AutoChangeNumberTxtView(context,edtTextView,txtnumberView);
        //regex=autoHighlighterText.findext();

        switch (Common.getCurrentExtention()) {
            case "html":
                dataType = getResources().getStringArray(R.array.html);
                break;
            case "txt":
                dataType = getResources().getStringArray(R.array.txt);
                break;
        }
        regex = new StringBuilder("\\b(");
        for (String word : dataType) {
            regex.append(Pattern.quote(word));
            regex.append("|");
        }
        regex.setLength(regex.length() - 1); // delete last added "|"
        regex.append(")\\b");
        edtTextView.addTextChangedListener(new TextWatcher() {
            ColorScheme keywords = new ColorScheme(

                    Pattern.compile(regex.toString()),
                    Color.CYAN
            );

            ColorScheme numbers = new ColorScheme(
                    Pattern.compile("(\\b(\\d*[.]?\\d+)\\b)"),
                    Color.BLUE
            );

            final ColorScheme[] schemes = {keywords, numbers};

            void removeSpans(Editable e, Class<? extends CharacterStyle> type) {
                CharacterStyle[] spans = e.getSpans(0, e.length(), type);
                for (CharacterStyle span : spans) {
                    e.removeSpan(span);
                }
            }

            class ColorScheme {
                final Pattern pattern;
                final int color;

                ColorScheme(Pattern pattern, int color) {
                    this.pattern = pattern;
                    this.color = color;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             //   autoCompleteText.autoOnchange(edtTextView, txtnumberView);
             //   autoCompleteText.autoComplete();
                autoChangeNumberTxtView.autoOnchange();
                textInType();


            }

            @Override
            public void afterTextChanged(Editable editable) {

                    removeSpans(editable, ForegroundColorSpan.class);
                    for (ColorScheme scheme : schemes) {
                        for (Matcher m = scheme.pattern.matcher(editable); m.find(); ) {
                            editable.setSpan(new ForegroundColorSpan(scheme.color),
                                    m.start(),
                                    m.end(),
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }


                }
            }

        });


    }



    public boolean textInType() {
                if (edtTextView.getText().length() == 0) {
                   // undoBtn.setEnabled(false);
                    undo_btn.setVisible(false);
                    redo_btn.setVisible(false);
                    save_btn.setVisible(false);
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
        save_btn.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_file_view, menu);
        undo_btn = (MenuItem) menu.findItem(R.id.action_undo_btn);
        redo_btn = (MenuItem) menu.findItem(R.id.action_redo_btn);
        save_btn = (MenuItem) menu.findItem(R.id.action_save_btn);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_as_btn:
                if(fileSaveDialog.saveAsDialog(edtTextView,save_btn)==true){
                 //   save_btn.setVisible(true);
                }
                return true;

            case R.id.action_save_btn:
                fileSaveDialog.saveDialog(edtTextView);
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
              //  findTextDialog.showFindDialog(edtTextView);
                Toast.makeText(NewFileActivity.this,Common.getCurrentExtention(),Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    }









