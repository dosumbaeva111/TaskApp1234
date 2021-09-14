package com.geek.taskapp.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Date;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.taskapp.databinding.ItemBinding;
import com.geek.taskapp.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> list = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
        if (position % 2 == 0){
            holder.binding.itemBg.setBackgroundColor(Color.BLACK);
        } else {
            holder.binding.itemBg.setBackgroundColor(Color.GRAY);
        }

        holder.itemView.setOnClickListener(v -> listener.onLongClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void addItems(List<Task> list ){
        this.list = list;
        notifyDataSetChanged();
    }

    public Task getItem(int position){
        return list.get(position);
    }

    public void addItem(Task task) {
        list.add(0,task);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBinding binding;
        public ViewHolder(@NonNull ItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Task task) {
            //Конвертирование времени
            String time = (String) android.text.format.DateFormat.format("HH:mm dd MMM yyyy", new Date(task.getCreatedAt()));
            binding.tvItem.setText(task.getTitle());
            binding.tvCreatedAt.setText(time);
        }
    }

    public interface OnItemClickListener{

        void onLongClick(int position);

        void onClick(int position);
    }
}
