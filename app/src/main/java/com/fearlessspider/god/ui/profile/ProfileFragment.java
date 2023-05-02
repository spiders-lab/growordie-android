package com.fearlessspider.god.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        final TextView textView = binding.textProfile;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}