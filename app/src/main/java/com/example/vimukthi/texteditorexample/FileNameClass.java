package com.example.vimukthi.texteditorexample;

import android.os.Environment;

public class FileNameClass {
    public static String currentName = "default.txt";
    public static String currentPath = Environment.getExternalStorageDirectory().getAbsolutePath() +"/vTextEditor";
}
