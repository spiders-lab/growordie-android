package com.fearlessspider.god.ui.journey;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Journey fragment model
 */
public class JourneyViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public JourneyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is journey fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}