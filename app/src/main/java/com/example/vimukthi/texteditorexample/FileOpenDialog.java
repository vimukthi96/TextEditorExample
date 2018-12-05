package com.example.vimukthi.texteditorexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileOpenDialog {
    Context context;
    Button btnGoToParent;
    Button cancelBtn;
    ListView dialogFileList;
    TextView txtFilePath;
    File root;
    File currentFoler;
    private List<String> fileList = new ArrayList<>();

    public FileOpenDialog(Context c) {
        this.context = c;
    }


    public void showDialog() {


        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        final AlertDialog ad=alertDialog.create();
        ad.setTitle("Open File");
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View layout_open_dialog = layoutInflater.inflate(R.layout.open_custom_dialog, null);
        txtFilePath = (TextView) layout_open_dialog.findViewById(R.id.folderPath);
        btnGoToParent = (Button) layout_open_dialog.findViewById(R.id.upBtn);
        dialogFileList = (ListView) layout_open_dialog.findViewById(R.id.list_item);
        cancelBtn=(Button)layout_open_dialog.findViewById(R.id.cancelDialog);
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        currentFoler = root;
        ListDir(currentFoler);

        btnGoToParent.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                ListDir(currentFoler.getParentFile());

            }
        });
        dialogFileList.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long l) {
                File selected = new File(fileList.get(position));
                if (selected.isDirectory()) {
                    ListDir(selected);
                } else {
                    Toast.makeText(context, selected.toString() , Toast.LENGTH_LONG).show();
                    String Path=selected.toString();
                    String[] part=Path.split("\\.");
                    String extention=part[1];
                    Common.setCurrentExtention(extention);
                    viewFile(selected);

                    ad.dismiss();

                }
            }
        });


        ad.setCanceledOnTouchOutside(true);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.dismiss();
            }
        });
        ad.setView(layout_open_dialog);
        ad.show();

    }
    private void ListDir(File file) {
        if(file.equals(root)){
            btnGoToParent.setEnabled(false);
        }
        else{
            btnGoToParent.setEnabled(true);
        }
        currentFoler=file;
        txtFilePath.setText(file.getPath());
        File[] files=file.listFiles();
        fileList.clear();
        for(File f:files){
            fileList.add(f.getPath());
        }
        ArrayAdapter<String>directoryList= new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,fileList);
        dialogFileList.setAdapter(directoryList);

    }
    public void viewFile(File path){
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            String textBody=sb.toString();
            Intent intent=new Intent(context,NewFileActivity.class);
            intent.putExtra("body", textBody);
            context.startActivity(intent);
           // colorChange colo=new colorChange();
            //colorChange.edtTextView.setText(textBody);
          //  edit.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}