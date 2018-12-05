package com.example.vimukthi.texteditorexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileOpenDialog {
    Context context;
    Button btnGoToParent;
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
        alertDialog.setTitle("Open File");
        alertDialog.setCancelable(true);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View layout_find = layoutInflater.inflate(R.layout.open_custom_dialog, null);
        txtFilePath = (TextView) layout_find.findViewById(R.id.folderPath);
        btnGoToParent = (Button) layout_find.findViewById(R.id.upBtn);
        dialogFileList = (ListView) layout_find.findViewById(R.id.list_item);
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
                            Toast.makeText(context, selected.toString() + "selected", Toast.LENGTH_LONG).show();

                        }
            }
        });

        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setView(layout_find);

        alertDialog.show();
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
}
