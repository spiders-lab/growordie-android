package com.fearlessspider.god.ui.exerciselist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fearlessspider.god.R;
import com.fearlessspider.god.db.Exercise;

public class ExerciseAdapter extends ListAdapter<Exercise, com.fearlessspider.god.ui.exerciselist.ExerciseAdapter.ViewHolder> {

    public ExerciseAdapter(@NonNull DiffUtil.ItemCallback<Exercise> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public com.fearlessspider.god.ui.exerciselist.ExerciseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return com.fearlessspider.god.ui.exerciselist.ExerciseAdapter.ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull com.fearlessspider.god.ui.exerciselist.ExerciseAdapter.ViewHolder holder, int position) {
        Exercise exercise = getItem(position);
        holder.bind(exercise.getComment());
    }

    static class ExerciseDiff extends DiffUtil.ItemCallback<Exercise> {
        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.getComment().equals(newItem.getComment());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;

        private ViewHolder(View view) {
            super(view);
            textViewName = itemView.findViewById(R.id.text_view_exercise_name);
        }

        public void bind(String text) {
            textViewName.setText(text);
        }

        static com.fearlessspider.god.ui.exerciselist.ExerciseAdapter.ViewHolder create(ViewGroup viewGroup) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_exercise_item, viewGroup, false);
            return new com.fearlessspider.god.ui.exerciselist.ExerciseAdapter.ViewHolder(view);
        }
    }
}