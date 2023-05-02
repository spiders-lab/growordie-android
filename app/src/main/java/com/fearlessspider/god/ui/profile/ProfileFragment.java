package com.fearlessspider.god.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fearlessspider.god.databinding.FragmentProfileBinding;

import java.util.Locale;

/**
 * Profile fragment
 */
public class ProfileFragment extends Fragment {

    public static final float DEFAULT_STEP_SIZE = Locale.getDefault() == Locale.US ? 2.5f : 75f;
    public static final String DEFAULT_STEP_UNIT =Locale.getDefault() == Locale.US ? "ft" : "cm" ;
    public static final float DEFAULT_GOAL = 10000;

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText inputUsername = binding.inputUsername;
        final TextView textViewStepsGoal = binding.textViewStepsGoal;
        final SeekBar seekBarStepsGoal = binding.seekBarStepsGoal;
        final Button buttonSave = binding.buttonSave;

        profileViewModel.getText().observe(getViewLifecycleOwner(), inputUsername::setText);
        seekBarStepsGoal.incrementProgressBy(1000);
        seekBarStepsGoal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 1000;
                progress = progress * 1000;
                textViewStepsGoal.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Saved.", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}