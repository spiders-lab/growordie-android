package com.fearlessspider.god.ui.splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.fearlessspider.god.MainActivity;
import com.fearlessspider.god.R;

/**
 * @class
 */
@SuppressLint("CustomSplashScreen")
public class SplashScreenFragment extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        runNextScreen();
    }

    private void runNextScreen() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenFragment.this, MainActivity.class));
            finish();
        }, 1500);
    }
}
