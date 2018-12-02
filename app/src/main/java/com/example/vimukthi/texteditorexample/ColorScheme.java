package com.example.vimukthi.texteditorexample;

import java.util.regex.Pattern;

public class ColorScheme {
    final Pattern pattern;
    final int color;

    ColorScheme(Pattern pattern, int color) {
        this.pattern = pattern;
        this.color = color;
    }
}
