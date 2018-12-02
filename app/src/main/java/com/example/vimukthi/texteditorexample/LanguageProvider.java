package com.example.vimukthi.texteditorexample;

public class LanguageProvider {

    public static Language getLanguage(String lang) {
        if (lang.equals("js") || lang.equals("javascript")) {
            return new js();
        } else if (lang.equals("html")) {
            return new html();
        }else if (lang.equals("css")) {
            return new css();
        } else {
            return new js();
        }

    }
}
