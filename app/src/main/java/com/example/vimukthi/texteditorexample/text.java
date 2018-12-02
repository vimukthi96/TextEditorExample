package com.example.vimukthi.texteditorexample;

import java.util.regex.Pattern;

public class text extends Language {
    private static final Pattern SYNTAX_NUMBERS = Pattern.compile(" ");
    private static final Pattern SYNTAX_SYMBOLS = Pattern.compile(" ");
    private static final Pattern SYNTAX_BRACKETS = Pattern.compile(" ");
    private static final Pattern SYNTAX_KEYWORDS = Pattern.compile(" "); //Слова без CASE_INSENSITIVE
    private static final Pattern SYNTAX_METHODS = Pattern.compile(
            "", Pattern.CASE_INSENSITIVE);
    private static final Pattern SYNTAX_STRINGS = Pattern.compile(" ");
    private static final Pattern SYNTAX_COMMENTS = Pattern.compile(" ");
    private static final char[] LANGUAGE_BRACKETS = new char[]{}; //do not change

    /**
     * Слова для автопродолжения кода.
     */

    public static final String[] CSS_KEYWORDS = new String[]{"vim%tyl" };


    /**
     * Соединение всех массивов в один. Этот массив и будет использоваться для
     * получения слов в редакторе.
     */
    private static final String[] ALL_KEYWORDS = ArrayUtils.join(String.class,CSS_KEYWORDS);

    public final Pattern getSyntaxNumbers() {
        return SYNTAX_NUMBERS;
    }

    public final Pattern getSyntaxSymbols() {
        return SYNTAX_SYMBOLS;
    }

    public final Pattern getSyntaxBrackets() {
        return SYNTAX_BRACKETS;
    }

    public final Pattern getSyntaxKeywords() {
        return SYNTAX_KEYWORDS;
    }

    public final Pattern getSyntaxMethods() {
        return SYNTAX_METHODS;
    }

    public final Pattern getSyntaxStrings() {
        return SYNTAX_STRINGS;
    }

    public final Pattern getSyntaxComments() {
        return SYNTAX_COMMENTS;
    }

    public final char[] getLanguageBrackets() {
        return LANGUAGE_BRACKETS;
    }

    public final String[] getAllCompletions() {
        return ALL_KEYWORDS;
    }

}
