package com.example.vimukthi.texteditorexample;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{


    Context c;
    ArrayList<row> arrayList;

    public CustomAdapter(Context context) {
        this.c=context;
        arrayList=new ArrayList<>();
        Resources res=c.getResources();
        String[] names=res.getStringArray(R.array.html);

        for(int i=0;i<names.length;i++){
            arrayList.add(new row(names[i]));
        }

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 =inflater.inflate(R.layout.customlistview,viewGroup,false);
        TextView t1=(TextView)view1.findViewById(R.id.textView);
        row temp=arrayList.get(i);
        t1.setText(temp.text);
        return view1;
    }
}