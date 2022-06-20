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

    private JourneyListViewModel journeyListViewModel;
    private FragmentJourneyListBinding binding;
    private JourneyAdapter journeyAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentJourneyListBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupListAdapter() {
        journeyListViewModel =
                new ViewModelProvider(this).get(JourneyListViewModel.class);
        journeyAdapter = new JourneyAdapter();
    }
}