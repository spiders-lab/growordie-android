package com.fearlessspider.god.ui.track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fearlessspider.god.databinding.FragmentTrackBinding;

/**
 * Track fragment
 */
public class TrackFragment extends Fragment {

    private FragmentTrackBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrackViewModel trackViewModel =
                new ViewModelProvider(this).get(TrackViewModel.class);

        binding = FragmentTrackBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTrack;
        trackViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}