package com.example.vimukthi.texteditorexample;


import java.util.regex.Pattern;

public abstract class Language {
    public abstract Pattern getSyntaxNumbers();
    public abstract Pattern getSyntaxSymbols();
    public abstract Pattern getSyntaxBrackets();
    public abstract Pattern getSyntaxKeywords();
    public abstract Pattern getSyntaxMethods();
    public abstract Pattern getSyntaxStrings();
    public abstract Pattern getSyntaxComments();
    public abstract char[] getLanguageBrackets();
    public abstract String[] getAllCompletions();
}
