package com.example.vimukthi.texteditorexample;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;



public class UndoAndRedo extends Fragment implements TextWatcher {

    private static final int TRACKING_READY = 100;
    private static final int TRACKING_ACTIVE = TRACKING_READY + 1;
    private static final int TRACKING_ENDING = TRACKING_READY + 2;

    private static final int TIMER_COUNTDOWN = 2000; //Time in milliseconds
    private static final int ARRAY_MAX_SIZE = 10;

    private Handler mHandler = null;
    private Runnable mRunnable = null;
    private int trackingState, tempStringStart, tempStringEnd;
    private String tempString;

    private CustomArrayDeque<String> undoArray, redoArray;
    private CustomArrayDeque<Integer> undoArrayIndex, redoArrayIndex;

    private View rootView;
    private EditText mEditText;

    static UndoAndRedo newInstance() {
        return new UndoAndRedo();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        trackingState = TRACKING_READY;

        if (undoArray == null) undoArray = new CustomArrayDeque<>(ARRAY_MAX_SIZE);
        if (redoArray == null) redoArray = new CustomArrayDeque<>(ARRAY_MAX_SIZE);
        if (undoArrayIndex == null) undoArrayIndex = new CustomArrayDeque<>(ARRAY_MAX_SIZE);
        if (redoArrayIndex == null) redoArrayIndex = new CustomArrayDeque<>(ARRAY_MAX_SIZE);

        if (mHandler == null) mHandler = new Handler();
        if (mRunnable == null) mRunnable = new Runnable() {
            @Override
            public void run() {
                tempStringEnd = mEditText.getSelectionEnd();
                if (tempString != null || !tempString.equals("")) {
                    undoArray.addFirst(tempString);
                    undoArrayIndex.addFirst(tempStringStart);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.error_generic), Toast.LENGTH_SHORT).show();
                }
                trackingState = TRACKING_READY;
            }
        };
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_new_file, container, false);

        mEditText = (EditText) rootView.findViewById(R.id.edtTextView);
        mEditText.addTextChangedListener(this);

        return rootView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (trackingState == TRACKING_READY) {
            tempStringStart = start;
            tempString = s.toString();
            mHandler.postDelayed(mRunnable, TIMER_COUNTDOWN);
            trackingState = TRACKING_ACTIVE;
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        switch (trackingState) {
            case TRACKING_READY:
                break;
            case TRACKING_ACTIVE:
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, TIMER_COUNTDOWN);
                break;
            case TRACKING_ENDING:
                trackingState = TRACKING_READY;
                break;
            default:
                trackingState = TRACKING_READY;
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        //Purposefully empty
    }

     void dispatchUndoEvent() {
        mHandler.removeCallbacks(mRunnable);
        trackingState = TRACKING_READY;

        if (undoArray.peek() != null) {
            tempString = undoArray.poll();
            tempStringStart = undoArrayIndex.poll();

            mEditText.setText(tempString);
            if (tempStringStart >= 0 && tempStringStart <= mEditText.length()) {
                mEditText.setSelection(tempStringStart);
            }

            redoArray.addFirst(tempString);
            redoArrayIndex.addFirst(tempStringStart);
        } else {
            Toast.makeText(getActivity(), getString(R.string.error_undo_queue_empty), Toast.LENGTH_SHORT).show();
        }
    }

    void dispatchRedoEvent() {
        mHandler.removeCallbacks(mRunnable);
        trackingState = TRACKING_READY;
        if (redoArray.peek() != null) {
            tempString = redoArray.poll();
            tempStringStart = redoArrayIndex.poll();

            mEditText.setText(tempString);
            if (tempStringStart >= 0 && tempStringStart <= mEditText.length()) {
                mEditText.setSelection(tempStringStart);
            }

            undoArray.addFirst(tempString);
            undoArrayIndex.addFirst(tempStringStart);
        } else {
            Toast.makeText(getActivity(), getString(R.string.error_redo_queue_empty), Toast.LENGTH_SHORT).show();
        }
    }
}