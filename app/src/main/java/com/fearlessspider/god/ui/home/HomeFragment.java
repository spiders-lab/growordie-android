package com.fearlessspider.god.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fearlessspider.god.BuildConfig;
import com.fearlessspider.god.R;
import com.fearlessspider.god.components.SensorListener;
import com.fearlessspider.god.databinding.FragmentHomeBinding;
import com.fearlessspider.god.repository.StepRepository;
import com.fearlessspider.god.utils.API26Wrapper;
import com.fearlessspider.god.utils.Logger;


/**
 * Home fragment
 */
public class HomeFragment extends Fragment implements SensorEventListener {

    private FragmentHomeBinding binding;

    private int todayoffset, since_boot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>=26){
            API26Wrapper.startForegroundService(getActivity(),new Intent(getActivity(), SensorListener.class));
        }
        else{
            getActivity().startService(new Intent(getActivity(), SensorListener.class));
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.stepsleft;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = getActivity().getSharedPreferences("G.O.D.", Context.MODE_PRIVATE);

        SensorManager sm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (sensor == null) {
            new AlertDialog.Builder(getActivity()).setTitle(R.string.no_sensor)
                    .setMessage(R.string.no_sensor_explain)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(final DialogInterface dialogInterface) {
                            getActivity().finish();
                        }
                    }).setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        } else {
            sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI, 0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        try{
            SensorManager sm=(SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            sm.unregisterListener(this);
        } catch (Exception e) {
            if(BuildConfig.DEBUG) Logger.log(e);
            e.printStackTrace();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(BuildConfig.DEBUG) Logger.log(
                "UI- sensorchanged | todatyoffset:" + todayoffset + "sinceboot:" + since_boot + sensorEvent.values[0]);
        if(sensorEvent.values[0]>Integer.MAX_VALUE || sensorEvent.values[0]==0){
            todayoffset = -(int) sensorEvent.values[0];
            StepRepository stepRepository = new StepRepository(this.requireActivity().getApplication());
            stepRepository.insert((int)sensorEvent.values[0]);
        }
        since_boot = (int)sensorEvent.values[0];
        // TODO: update pie
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}