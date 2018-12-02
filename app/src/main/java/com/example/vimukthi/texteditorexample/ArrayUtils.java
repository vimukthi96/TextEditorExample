package com.example.vimukthi.texteditorexample;

import android.support.annotation.NonNull;

import java.lang.reflect.Array;

public class ArrayUtils {

    @SafeVarargs
    public static <T> T[] join(Class<T> c, @NonNull T[]... objects) {
        int size = 0;
        for (T[] object : objects) {
            size += object.length;
        }
        T[] result = (T[]) Array.newInstance(c, size);
        int index = 0;
        for (T[] object : objects) {
            for (T t : object) {
                Array.set(result, index, t);
                index++;
            }
        }
        return result;
    }
}
