package com.example.vimukthi.texteditorexample;

import java.util.ArrayDeque;

public class CustomArrayDeque<T> extends ArrayDeque<T> {
    private final int maxSize;

    public CustomArrayDeque(int maxSize) {
        super(maxSize);
        this.maxSize = maxSize;
    }

    @Override
    public void addFirst(T t) {
        if (maxSize == size()) {
            removeLast();
        }
        super.addFirst(t);
    }

    @Override
    public void addLast(T t) {
        if (maxSize == size()) {
            removeFirst();
        }
        super.addLast(t);
    }
}
