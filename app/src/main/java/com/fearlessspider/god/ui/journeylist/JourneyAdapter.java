package com.fearlessspider.god.ui.journeylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fearlessspider.god.R;
import com.fearlessspider.god.db.Journey;


public class JourneyAdapter extends ListAdapter<Journey, JourneyAdapter.ViewHolder> {

    public JourneyAdapter(@NonNull DiffUtil.ItemCallback<Journey> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public JourneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull JourneyAdapter.ViewHolder holder, int position) {
        Journey journey = getItem(position);
        holder.bind(journey.getName());
    }

    static class JourneyDiff extends DiffUtil.ItemCallback<Journey> {
        @Override
        public boolean areItemsTheSame(@NonNull Journey oldItem, @NonNull Journey newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Journey oldItem, @NonNull Journey newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;

        private ViewHolder(View view) {
            super(view);
            textViewName = itemView.findViewById(R.id.text_view_journey_name);
        }

        public void bind(String text) {
            textViewName.setText(text);
        }

        static ViewHolder create(ViewGroup viewGroup) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_journey_item, viewGroup, false);
            return new ViewHolder(view);
        }
    }
}
