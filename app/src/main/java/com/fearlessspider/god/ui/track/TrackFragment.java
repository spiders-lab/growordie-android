package com.fearlessspider.god.ui.track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fearlessspider.god.databinding.FragmentJourneyBinding;


/**
 * Track fragment
 */
public class TrackFragment extends Fragment {

    private FragmentJourneyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrackViewModel trackViewModel =
                new ViewModelProvider(this).get(TrackViewModel.class);

        binding = FragmentJourneyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textJourney;
        trackViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}