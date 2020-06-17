package com.ymg.personaldictionary.ui.randomWord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class randomWordViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public randomWordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}