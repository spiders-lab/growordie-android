package com.fearlessspider.god.ui.journey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


/**
 * Journey fragment
 */
public class JourneyFragment extends Fragment {

    private FragmentJourneyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        JourneyViewModel journeyViewModel =
                new ViewModelProvider(this).get(JourneyViewModel.class);

        binding = FragmentJourneyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textJourney;
        journeyViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}