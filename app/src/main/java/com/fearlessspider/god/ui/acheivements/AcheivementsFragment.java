package com.fearlessspider.god.ui.acheivements;

import androidx.fragment.app.Fragment;

import com.fearlessspider.god.databinding.FragmentAcheivementsBinding;

public class AcheivementsFragment extends Fragment {

    private FragmentAcheivementsBinding binding;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
