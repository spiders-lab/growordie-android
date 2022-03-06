package com.fearlessspider.god.ui.track;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Track fragment model
 */
public class TrackViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrackViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is track fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}