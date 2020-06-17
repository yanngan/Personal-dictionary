package com.ymg.personaldictionary.ui.addWord;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class addWordViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public addWordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add word fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}