package com.fearlessspider.god.ui.exerciselist;

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
import com.fearlessspider.god.databinding.FragmentExerciseListBinding;
import com.fearlessspider.god.db.Exercise;
import com.fearlessspider.god.ui.exerciselist.ExerciseAdapter;
import com.fearlessspider.god.ui.exerciselist.ExerciseListViewModel;

import java.util.List;

/**
 * Exercise List fragment
 */
public class ExerciseListFragment extends Fragment {

    private ExerciseListViewModel exerciseListViewModel;
    private FragmentExerciseListBinding binding;
    private ExerciseAdapter exerciseAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExerciseListBinding.inflate(inflater, container, false);

        RecyclerView recycleView = getView().findViewById(R.id.recycleView);
        exerciseAdapter = new ExerciseAdapter(new ExerciseAdapter.ExerciseDiff());
        recycleView.setAdapter(exerciseAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupListAdapter() {
        exerciseListViewModel =
                new ViewModelProvider(this).get(ExerciseListViewModel.class);
        exerciseListViewModel.getAll().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exerciseList) {
                exerciseAdapter.submitList(exerciseList);
            }
        });
    }
}