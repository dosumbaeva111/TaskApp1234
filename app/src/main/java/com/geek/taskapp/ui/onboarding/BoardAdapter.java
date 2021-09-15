package com.geek.taskapp.ui.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.taskapp.R;
import com.geek.taskapp.databinding.ListBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private String[] titles = new String[]{"SIMPLE ABROAD CALLS", "FREE WONEP TO WONEP", "NO HIDDEN CHARGES OR FEES"};
    private String[] descriptions = new String[]{"Wonep converts international calls to local calls without WiFi or data", "If the person you're calling also has Wonep the call will be entirely free", "We have a very small charge for non-Wonep calls to mobiles or landlines"};
    private int[] imageViews = new int[]{R.drawable.onboard_page1, R.drawable.onboard_page2,R.drawable.onboard_page3};
    private OnStartClickListener onStartClickListener;

    public void setOnStartClickListener(OnStartClickListener onStartClickListener) {
        this.onStartClickListener = onStartClickListener;
    }

    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListBoardBinding binding;
        public ViewHolder(@NonNull ListBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(int position) {
            binding.textTitle.setText(titles[position]);
            binding.textDescription.setText(descriptions[position]);
            binding.imageView.setImageResource(imageViews[position]);

            if (position == 2){
                binding.btnStart.setVisibility(View.VISIBLE);
            } else
                binding.btnStart.setVisibility(View.INVISIBLE);

            binding.btnStart.setOnClickListener(v -> {
                onStartClickListener.onClick();
            });
        }
    }

    public interface OnStartClickListener{
        void onClick();
    }
}
