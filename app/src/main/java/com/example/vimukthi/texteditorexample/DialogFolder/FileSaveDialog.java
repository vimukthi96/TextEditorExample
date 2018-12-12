package com.example.vimukthi.texteditorexample.DialogFolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.vimukthi.texteditorexample.Common;
import com.example.vimukthi.texteditorexample.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSaveDialog  {
    RelativeLayout relativeLayout;
    EditText editTextView;
    String FilePath;
    String FileName;
    String FileBody;
    MenuItem saveBtn;


    Context context;
    public FileSaveDialog(Context con) {
        this.context =con;
     //   FilePath= Environment.getExternalStorageDirectory().getAbsolutePath() +"/vTextEditor";

    }

    public boolean saveAsDialog(EditText edtTextView, MenuItem save) {
        editTextView =edtTextView;
        saveBtn=save;
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Save As");


        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View layout_save_as = layoutInflater.inflate(R.layout.dialog_save_as, null);

        final MaterialEditText edtTextName = layout_save_as.findViewById(R.id.dialog_name);
        final MaterialEditText edtFolderPath = layout_save_as.findViewById(R.id.dialog_path);

        alertDialog.setView(layout_save_as);
        FilePath= Environment.getExternalStorageDirectory().getAbsolutePath() +"/vTextEditor";
        edtFolderPath.setText(FilePath);

        alertDialog.setPositiveButton("Save As", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


                if (TextUtils.isEmpty(edtTextName.getText())) {
                     Toast.makeText(context,"TExt field is empty", Toast.LENGTH_SHORT).show();
                //    Snackbar.make(relativeLayout,"name text field is empty or space",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edtFolderPath.getText())) {
                     Toast.makeText(context,"TExt field is empty", Toast.LENGTH_SHORT).show();
                   // Snackbar.make(relativeLayout,"folder path text field is empty or space",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // final SpotsDialog waitingDialog=new SpotsDialog(NewFileActivity.this);
                // waitingDialog.show();
                //FlieSaveDialog fileSaveDialog = null;

                //fileSaveDialog.
                FileName = edtTextName.getText().toString();
                FilePath = edtFolderPath.getText().toString();
                FileBody = editTextView.getText().toString();


                createPDF(FileName,FilePath,FileBody);

                //  saveDialog(edtTextName.getText().toString(),edtFolderPath.getText().toString());
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
    public boolean saveDialog( EditText edtTextView) {
        editTextView =edtTextView;

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Save ");
       // LayoutInflater layoutInflater = LayoutInflater.from(context);
       // View layout_save_as = layoutInflater.inflate(R.layout.dialog_save_as, null);

      //  final MaterialEditText edtTextName = layout_save_as.findViewById(R.id.dialog_name);
       // final MaterialEditText edtFolderPath = layout_save_as.findViewById(R.id.dialog_path);

       // alertDialog.setView(layout_save_as);

      //  edtFolderPath.setText(FilePath);
       // alertDialog.setMessage("Do you want to save?");

        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            //    FileName = edtTextName.getText().toString();
            //    FilePath = edtFolderPath.getText().toString();
                FileBody = editTextView.getText().toString();
                createPDF(FileName,FilePath,FileBody);

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

    public void createPDF(String fileName,String filePath,String fileBody){
        String name=fileName;
        String path=filePath ;
        String body=fileBody;

        File dir =new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }

        File file =new File(path,name);

        String parts[]=name.split("\\.");
        String extention=parts[1];
        Common.setCurrentExtention(extention);
        saveBtn.setVisible(true);


        try {
            FileOutputStream outputStream;

            outputStream = new FileOutputStream(file);
            outputStream.write(body.getBytes());
            outputStream.close();
            Toast.makeText(context, "Saved" , Toast.LENGTH_SHORT).show();
            //  return true;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "File Not Found", Toast.LENGTH_SHORT).show();
            //    return false;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error Saving", Toast.LENGTH_SHORT).show();
            //    return false;
        }


    }
}
