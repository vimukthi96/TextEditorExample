package com.example.vimukthi.texteditorexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.vimukthi.texteditorexample.DialogFolder.FileSaveDialog;
import com.example.vimukthi.texteditorexample.DialogFolder.FindTextDialog;

public class NewFileActivity extends colorChange {

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

    Common common;
    String aBoolean="false";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent inte=getIntent();
        String body=inte.getStringExtra("body");
        aBoolean= inte.getStringExtra("bool");
        edtTextView.setText(body);
        relativeLayout = (RelativeLayout) findViewById(R.id.layout_root);
        helper = new TextViewUndoRedo(edtTextView);
        context = NewFileActivity.this;
        fileSaveDialog = new FileSaveDialog(context);
        findTextDialog = new FindTextDialog(context);
       // autoCompleteText = new AutoCompleteText(context,edtTextView);


        edtTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInType();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             //   autoCompleteText.autoOnchange(edtTextView, txtnumberView);
           //     autoCompleteText.autoComplete();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });



        common=new Common();
        common.setListener(new Common.ChangeListener() {
            @Override
            public void onChange() {
                recreate();
            }
        });



    }

    @Override
    public void recreate() {
        super.recreate();
        save_btn.setVisible(true);

    }


    @Override
    public void onBackPressed() {

        Common.setCurrentExtention("txt");
        super.onBackPressed();


    }
    /*@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        save_btn.setVisible(true);
    }*/


    public boolean textInType() {
                if (edtTextView.getText().length() == 0) {
                   // undoBtn.setEnabled(false);
                  //  undo_btn.setVisible(false);
                //   redo_btn.setVisible(false);
                /*    save_btn.setVisible(false);*/
                    return false;
                } else {
                 /*   if(aBoolean.equals("true")){
                      //  undo_btn.setVisible(false);
                        return  true;

                    }else {*/
                    undo_btn.setVisible(true);
                    return true;
                 //   }
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
                if(fileSaveDialog.saveAsDialog(edtTextView,save_btn)==true) {

                }


                return true;

            case R.id.action_save_btn:
                fileSaveDialog.saveDialog(edtTextView);
                return true;

            case R.id.action_undo_btn:
                performUndo();
                redo_btn.setVisible(true);
                return true;

            case R.id.action_redo_btn:
                performRedo();
                return true;

            case R.id.action_find_btn:
               // findTextDialog.showFindDialog(edtTextView);
                Toast.makeText(NewFileActivity.this,aBoolean,Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}









