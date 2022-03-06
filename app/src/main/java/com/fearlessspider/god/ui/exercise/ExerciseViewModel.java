package com.fearlessspider.god.ui.exercise;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Exercise fragment model
 */
public class ExerciseViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ExerciseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is exercise fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}