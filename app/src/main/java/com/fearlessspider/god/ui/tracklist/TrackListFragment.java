package com.fearlessspider.god.ui.tracklist;

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
import com.fearlessspider.god.databinding.FragmentTrackListBinding;
import com.fearlessspider.god.db.Track;

import java.util.List;

/**
 * Track List fragment
 */
public class TrackListFragment extends Fragment {

    private TrackListViewModel trackListViewModel;
    private FragmentTrackListBinding binding;
    private TrackAdapter trackAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTrackListBinding.inflate(inflater, container, false);

        RecyclerView recycleView = getView().findViewById(R.id.recycleView);
        trackAdapter = new TrackAdapter(new TrackAdapter.TrackDiff());
        recycleView.setAdapter(trackAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupListAdapter() {
        trackListViewModel =
                new ViewModelProvider(this).get(TrackListViewModel.class);
        trackListViewModel.getAll().observe(this, new Observer<List<Track>>() {
            @Override
            public void onChanged(List<Track> trackList) {
                trackAdapter.submitList(trackList);
            }
        });
    }
}