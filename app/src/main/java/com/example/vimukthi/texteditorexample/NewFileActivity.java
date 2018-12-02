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

public class NewFileActivity extends colorChange {

    public TextView txtnumberView;
   // public MultiAutoCompleteTextView  edtTextView;
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
     //   setContentView(R.layout.activity_new_file);

        txtnumberView = (TextView) findViewById(R.id.numberViewText);
       // edtTextView = (MultiAutoCompleteTextView) findViewById(R.id.edtTextView);
        relativeLayout = (RelativeLayout) findViewById(R.id.layout_root);
        helper = new TextViewUndoRedo(edtTextView);
        context = NewFileActivity.this;
        fileSaveDialog = new FileSaveDialog(context);
        findTextDialog = new FindTextDialog(context);
        autoCompleteText = new AutoCompleteText(context,edtTextView);
        autoChangeNumberTxtView=new AutoChangeNumberTxtView(context,edtTextView,txtnumberView);


        edtTextView.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             //   autoCompleteText.autoOnchange(edtTextView, txtnumberView);
                autoCompleteText.autoComplete();
                autoChangeNumberTxtView.autoOnchange();
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









