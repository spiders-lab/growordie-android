package com.fearlessspider.god.ui.journeylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fearlessspider.god.R;
import com.fearlessspider.god.db.Journey;

import java.util.ArrayList;
import java.util.List;


public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.ViewHolder> {
    private List<Journey> journeyList = new ArrayList<>();

    @NonNull
    @Override
    public JourneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_journey_item, parent, false);
        return new JourneyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JourneyAdapter.ViewHolder holder, int position) {
        Journey journey = journeyList.get(position);
        holder.textViewName.setText(journey.getName());
    }

    @Override
    public int getItemCount() {
        return journeyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;

        private ViewHolder(View view) {
            super(view);
            textViewName = itemView.findViewById(R.id.text_view_journey_name);
        }
    }
}
