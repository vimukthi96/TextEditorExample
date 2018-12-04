package com.example.vimukthi.texteditorexample;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class colorChange extends AppCompatActivity {

    MultiAutoCompleteTextView edtTextView;
    String[] keyword;
    // private ICodeEditorTextChange codeEditorTextChange;
    Context context;
    LanguageProvider languageProvider;
    StringBuilder regex;
    Common common;
    MenuItem save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_file);
        edtTextView = (MultiAutoCompleteTextView) findViewById(R.id.edtTextView);
        languageProvider=new LanguageProvider();

        keyword=languageProvider.getLanguage(Common.getCurrentExtention()).getAllCompletions();
        regex = new StringBuilder("\\b(");
        for (String word : keyword) {
            regex.append(Pattern.quote(word));
            regex.append("|");
        }
        regex.setLength(regex.length() - 1); // delete last added "|"
        regex.append(")\\b");
        edtTextView.addTextChangedListener(new TextWatcher() {
            ColorScheme AllCompletions=new ColorScheme(
                    Pattern.compile(regex.toString()),
                    getResources().getColor(R.color.syntaxWord)
            );
            ColorScheme SyntaxKeyword = new ColorScheme(
                    languageProvider.getLanguage(Common.getCurrentExtention()).getSyntaxKeywords(),
                    getResources().getColor(R.color.syntaxKeywords)
            );
            ColorScheme SyntexNumbers = new ColorScheme(
                    languageProvider.getLanguage(Common.getCurrentExtention()).getSyntaxNumbers(),
                    getResources().getColor(R.color.syntaxNumbers)
            );
            ColorScheme SyntexBrackets = new ColorScheme(
                    languageProvider.getLanguage(Common.getCurrentExtention()).getSyntaxBrackets(),
                    getResources().getColor(R.color.syntaxBrackets)
            );
            ColorScheme SyntexMethods = new ColorScheme(
                    languageProvider.getLanguage(Common.getCurrentExtention()).getSyntaxMethods(),
                    getResources().getColor(R.color.syntaxMethods)
            );
            ColorScheme SyntexComments = new ColorScheme(
                    languageProvider.getLanguage(Common.getCurrentExtention()).getSyntaxComments(),
                    getResources().getColor(R.color.syntaxComments)
            );
            ColorScheme SyntexStrings = new ColorScheme(
                    languageProvider.getLanguage(Common.getCurrentExtention()).getSyntaxStrings(),
                    getResources().getColor(R.color.syntaxStrings)
            );
            ColorScheme SyntexSymbols = new ColorScheme(
                    languageProvider.getLanguage(Common.getCurrentExtention()).getSyntaxSymbols(),
                    getResources().getColor(R.color.syntaxSymbols)
            );


            final ColorScheme[] schemes = {SyntaxKeyword, SyntexNumbers, SyntexBrackets, SyntexStrings, SyntexComments, SyntexMethods, SyntexSymbols, AllCompletions};


            void removeSpans(Editable e, Class<? extends CharacterStyle> type) {
                CharacterStyle[] spans = e.getSpans(0, e.length(), type);
                for (CharacterStyle span : spans) {
                    e.removeSpan(span);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                removeSpans(editable, ForegroundColorSpan.class);
                for (ColorScheme scheme : schemes) {
                    for (Matcher m = scheme.pattern.matcher(editable); m.find(); ) {
                        editable.setSpan(new ForegroundColorSpan(scheme.color),
                                m.start(),
                                m.end(),
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }

                }
            }
        });

    }

}
