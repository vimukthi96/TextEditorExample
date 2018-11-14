package com.example.vimukthi.texteditorexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.xeoh.android.texthighlighter.TextHighlighter;

import dmax.dialog.SpotsDialog;

public class NewFileActivity extends AppCompatActivity {

    public TextView txtnumberView;
    public EditText edtTextView;
    UndoAndRedo undoAndRedo;
    RelativeLayout relativeLayout;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_file_view,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_save_btn:
                Toast.makeText(this, "hehdejd jehfgehkd", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_undo_btn:
               // undoAndRedo.dispatchUndoEvent();
                return true;
            case R.id.action_redo_btn:
              //  undoAndRedo.dispatchRedoEvent();
                return true;
            case R.id.action_find_btn:
                textHihjlight();
                //showFindDialog();
                return true;
        }
    return super.onOptionsItemSelected(item);
    }

    private void showFindDialog() {
        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle("Find");
      //  alertDialog.setMessage("please use email to sign in");

        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View layout_find= layoutInflater.inflate(R.layout.dialog_find_text,null) ;

        final MaterialEditText edtTextSEarch= layout_find.findViewById(R.id.reg_email);
       // final MaterialEditText edtPassward=layout_sign_in.findViewById(R.id.reg_passward) ;

        alertDialog.setView(layout_find);

        alertDialog.setPositiveButton("Find", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


            /*    if (TextUtils.isEmpty(edtTextSEarch.getText().toString())) {
                    Snackbar.make(relativeLayout, "Please enter some word to find ", Snackbar.LENGTH_SHORT).show();
                    return;
                }*/

               // final SpotsDialog waitingDialog=new SpotsDialog(NewFileActivity.this);
               // waitingDialog.show();

                 Toast.makeText(NewFileActivity.this,edtTextSEarch.getText().toString(),Toast.LENGTH_SHORT).show();
               // textHihjlight();
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

    boolean isHighlight=false;
    TextHighlighter textHighlighter;
    private void textHihjlight() {
        if(!isHighlight) {
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
        isHighlight =! isHighlight;
    }
    }



