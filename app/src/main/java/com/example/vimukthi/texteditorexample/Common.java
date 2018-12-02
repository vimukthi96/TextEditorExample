package com.example.vimukthi.texteditorexample;

public class Common {

    static String currentExtention = "txt";
    static ChangeListener listener;

    public static String getCurrentExtention() {
        return currentExtention;
    }

    public static void setCurrentExtention(String extention) {
        currentExtention = extention;

        if (listener != null) listener.onChange();
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }


    public interface ChangeListener {
        void onChange();
    }
}

