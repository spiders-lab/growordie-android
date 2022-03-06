package com.fearlessspider.god.ui.journeylist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Journey List fragment model
 */
public class JourneyListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public JourneyListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is journey list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}