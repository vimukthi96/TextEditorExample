package com.example.vimukthi.texteditorexample;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Action implements ActionMode.Callback {
    ActionMode mActionMode;
    Context c;
    FindTextDialog findTextDialog;
    EditText edtTextView;
    Spannable ss;
    public Action(Context c,EditText edt) {
        this.c = c;
        edtTextView=edt;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_remove_highlight:
                removeHighlight();
                actionMode.finish(); // Action picked, so close the CAB
                return true;
            default:
                return false;
        }
    }
    public boolean removeHighlight() {

        Matcher matcher;
        String text = edtTextView.getText().toString();
        ss = new SpannableString(text);
      //  String textToSearch = subString;
        Pattern pattern = Pattern.compile(text);
        matcher = pattern.matcher(ss);
            while (matcher.find()) {
                ss.setSpan(new BackgroundColorSpan(Color.TRANSPARENT), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            edtTextView.setText(ss);
        return true;
    }


    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        mActionMode = null;
    }
}
