package com.fearlessspider.god.ui.journeylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fearlessspider.god.R;
import com.fearlessspider.god.db.Journey;
import com.fearlessspider.god.databinding.FragmentJourneyListBinding;

import java.util.List;

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

        RecyclerView recycleView = binding.recycleView;
        journeyAdapter = new JourneyAdapter(new JourneyAdapter.JourneyDiff());
        recycleView.setAdapter(journeyAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        journeyListViewModel.getAll().observe(this, new Observer<List<Journey>>() {
            @Override
            public void onChanged(List<Journey> journeyList) {
                journeyAdapter.submitList(journeyList);
            }
        });
    }
}