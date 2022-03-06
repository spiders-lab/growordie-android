package com.fearlessspider.god.ui.journeylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fearlessspider.god.databinding.FragmentJourneyListBinding;

/**
 * Journey List fragment
 */
public class JourneyListFragment extends Fragment {

    private FragmentJourneyListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        JourneyListViewModel journeyListViewModel =
                new ViewModelProvider(this).get(JourneyListViewModel.class);

        binding = FragmentJourneyListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textJourneyList;
        journeyListViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}