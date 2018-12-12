package com.example.vimukthi.texteditorexample;

import com.example.vimukthi.texteditorexample.LanguageFolder.Language;
import com.example.vimukthi.texteditorexample.LanguageFolder.css;
import com.example.vimukthi.texteditorexample.LanguageFolder.html;
import com.example.vimukthi.texteditorexample.LanguageFolder.js;
import com.example.vimukthi.texteditorexample.LanguageFolder.text;

public class LanguageProvider {

    public static Language getLanguage(String lang) {
        if (lang.equals("js") || lang.equals("javascript")) {
            return new js();
        } else if (lang.equals("html") || lang.equals("htm")) {
            return new html();
        }else if (lang.equals("css")) {
            return new css();
        }
        else {
            return new text();
        }

    }
}
