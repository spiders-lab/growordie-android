package com.fearlessspider.god.ui.tracklist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fearlessspider.god.R;
import com.fearlessspider.god.db.Track;


public class TrackAdapter extends ListAdapter<Track, TrackAdapter.ViewHolder> {

    public TrackAdapter(@NonNull DiffUtil.ItemCallback<Track> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TrackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackAdapter.ViewHolder holder, int position) {
        Track track = getItem(position);
        holder.bind(track.getName());
    }

    static class TrackDiff extends DiffUtil.ItemCallback<Track> {
        @Override
        public boolean areItemsTheSame(@NonNull Track oldItem, @NonNull Track newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Track oldItem, @NonNull Track newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;

        private ViewHolder(View view) {
            super(view);
            textViewName = itemView.findViewById(R.id.text_view_track_name);
        }

        public void bind(String text) {
            textViewName.setText(text);
        }

        static ViewHolder create(ViewGroup viewGroup) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_track_item, viewGroup, false);
            return new ViewHolder(view);
        }
    }
}
