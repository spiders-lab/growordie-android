package com.fearlessspider.god.ui.acheivements;

import androidx.fragment.app.Fragment;

import com.fearlessspider.god.databinding.FragmentAchievementsBinding;

public class AchievementsFragment extends Fragment {

    private FragmentAchievementsBinding binding;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
