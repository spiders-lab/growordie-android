package com.fearlessspider.god.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.fearlessspider.god.ui.profile.ProfileFragment;
import com.fearlessspider.god.utils.API26Wrapper;
import com.fearlessspider.god.utils.Logger;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * Home fragment
 */
public class HomeFragment extends Fragment implements SensorEventListener {

    private FragmentHomeBinding binding;
    public static NumberFormat formatter=NumberFormat.getInstance(Locale.getDefault());
    private ImageView levels, dialog;
    private TextView stepsView, totalView, averageView, calories, stepsleft;
    private PieModel sliceGoal, sliceCurrent;
    private PieChart pg;
    private ProgressBar progressBar;
    public static int totalstepsgoal=0;
    private int todayoffset, total_start, goal, since_boot, totaldays = 1, goalreach;
    private boolean showSteps = true;

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

        levels=binding.levels;
        stepsView=binding.stepsinpiechart;
        totalView=binding.total;
        averageView=binding.average;
        progressBar=binding.progressBar;
        stepsleft=binding.stepsleft;
        calories=binding.calories;
        dialog=binding.splitcounter;
        pg=binding.graph;
        setPiechart();
        homeViewModel.getText().observe(getViewLifecycleOwner(), stepsleft::setText);
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

        goal = prefs.getInt("goal", (int) ProfileFragment.DEFAULT_GOAL);

        int pauseDifference = since_boot - prefs.getInt("pauseCount", since_boot);

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

        since_boot -= pauseDifference;

        stepsDistanceChanges();
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
        updatePie();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void stepsDistanceChanges() {
        if(showSteps){
            ((TextView) getView().findViewById(R.id.unit)).setText(getString(R.string.steps));

        }else{
            String unit=getActivity().getSharedPreferences("G.O.D.",Context.MODE_PRIVATE)
                    .getString("stepsize_unit",ProfileFragment.DEFAULT_STEP_UNIT);
            if(unit.equals("cm")){
                unit="km";
            }else{
                unit="mile";
            }
            ((TextView) getView().findViewById(R.id.unit)).setText(unit);
        }
        updatePie();
        updateBars();

    }

    private void updatePie() {
        if(BuildConfig.DEBUG) Logger.log("UI-updatesteps:"+since_boot);
        int steps_today=Math.max(todayoffset+since_boot,0);
        sliceCurrent.setValue(steps_today);
        if(goal-steps_today>0){
            if(pg.getData().size()==1){
                pg.addPieSlice(sliceGoal);
            }
            sliceGoal.setValue(goal-steps_today);}
        else{
            pg.clearChart();
            pg.addPieSlice(sliceCurrent);
        }
        pg.update();
        if(showSteps){
            stepsView.setText(formatter.format(steps_today));
            double kcal=steps_today*0.04;
            calories.setText(formatter.format(kcal));
            totalView.setText(formatter.format(total_start+steps_today));
            averageView.setText(formatter.format((total_start+steps_today)/totaldays));
            totalstepsgoal=total_start+steps_today;
            if(totalstepsgoal<3000){
                levels.setBackgroundColor(Color.GRAY);
                levels.setImageResource(R.drawable.editthree);
                goalreach=3000;
            }
            if(totalstepsgoal>=3000 && totalstepsgoal<7000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editthree);
                goalreach=7000;
            }
            if(totalstepsgoal>=7000 && totalstepsgoal<10000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editseven);
                goalreach=10000;
            }
            if(totalstepsgoal>=10000 && totalstepsgoal<14000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.edit10);
                goalreach=14000;
            }
            if(totalstepsgoal>=14000 && totalstepsgoal<20000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editfort);
                goalreach=20000;
            }
            if(totalstepsgoal>=20000 && totalstepsgoal<30000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.edittwenty);
                goalreach=30000;
            }
            if(totalstepsgoal>=30000 && totalstepsgoal<40000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editthirty);
                goalreach=40000;
            }
            if(totalstepsgoal>=40000 && totalstepsgoal<60000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editforty);
                goalreach=60000;
            }
            if(totalstepsgoal>=60000 && totalstepsgoal<70000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editforty);
                goalreach=700000;
            }
            float set=((totalstepsgoal*100)/goalreach);
            int b=(int)Math.round(set);
            stepsleft.setText(formatter.format(goalreach-totalstepsgoal));
            progressBar.setProgress(b);
        }
        else{
            SharedPreferences prefs=getActivity().getSharedPreferences("pedometer",Context.MODE_PRIVATE);
            float stepsize=prefs.getFloat("stepsize_value",ProfileFragment.DEFAULT_STEP_SIZE);
            float distance_today=steps_today*stepsize;
            float distance_total=(steps_today+total_start)*stepsize;
            if(prefs.getString("stepsize_unit",ProfileFragment.DEFAULT_STEP_UNIT).equals("cm"))
            {
                distance_today/=100000;
                distance_total/=100000;

            }else{
                distance_today /= 5280;
                distance_total /= 5280;

            }
            stepsView.setText(formatter.format(distance_today));
            totalView.setText(formatter.format(distance_total));
            averageView.setText(formatter.format(distance_total / totaldays));
            totalstepsgoal=total_start+steps_today;
            if(totalstepsgoal<3000){
                levels.setBackgroundColor(Color.GRAY);
                levels.setImageResource(R.drawable.editthree);
                goalreach=3000;
            }
            if(totalstepsgoal>=3000 && totalstepsgoal<7000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editthree);
                goalreach=7000;
            }
            if(totalstepsgoal>=7000 && totalstepsgoal<10000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editseven);
                goalreach=10000;
            }
            if(totalstepsgoal>=10000 && totalstepsgoal<14000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.edit10);
                goalreach=14000;
            }
            if(totalstepsgoal>=14000 && totalstepsgoal<20000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editfort);
                goalreach=20000;
            }
            if(totalstepsgoal>=20000 && totalstepsgoal<30000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.edittwenty);
                goalreach=30000;
            }
            if(totalstepsgoal>=30000 && totalstepsgoal<40000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editthirty);
                goalreach=40000;
            }
            if(totalstepsgoal>=40000 && totalstepsgoal<60000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editforty);
                goalreach=60000;
            }
            if(totalstepsgoal>=60000 && totalstepsgoal<70000){
                levels.setBackgroundColor(Color.BLUE);
                levels.setImageResource(R.drawable.editforty);
                goalreach=700000;
            }
            float set=((totalstepsgoal*100)/goalreach);
            int b=(int)Math.round(set);
            stepsleft.setText(formatter.format(goalreach-totalstepsgoal));
            progressBar.setProgress(b);
        }
    }

    private void updateBars() {

        SimpleDateFormat df= new SimpleDateFormat("E",Locale.getDefault());
        BarChart barChart =(BarChart) getView().findViewById(R.id.bargraph);
        if(barChart.getData().size()>0) barChart.clearChart();
        int steps;
        float distance ,stepsize=ProfileFragment.DEFAULT_STEP_SIZE;
        boolean stepsize_cm=true;
        if(!showSteps){
            SharedPreferences prefs = getActivity().getSharedPreferences("pedometer",Context.MODE_PRIVATE);
            stepsize=prefs.getFloat("stepsize_value",ProfileFragment.DEFAULT_STEP_SIZE);
            stepsize_cm=prefs.getString("stepsize_unit",ProfileFragment.DEFAULT_STEP_UNIT).equals("cm");}
        barChart.setShowDecimal(!showSteps);
    }

    private void setPiechart() {
        sliceCurrent=new PieModel(0, Color.parseColor("#69F0AE"));
        pg.addPieSlice(sliceCurrent);
        sliceGoal=new PieModel(ProfileFragment.DEFAULT_GOAL, Color.parseColor("#40C4FF"));
        pg.addPieSlice(sliceGoal);
        pg.setDrawValueInPie(false);
        pg.setUsePieRotation(true);
        pg.startAnimation();
    }
}