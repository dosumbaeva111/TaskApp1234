package com.geek.taskapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geek.taskapp.R;
import com.geek.taskapp.databinding.FragmentTaskBinding;
import com.geek.taskapp.models.Task;

public class TaskFragment extends Fragment {
    private FragmentTaskBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSave.setOnClickListener(v -> {
            save();
        });
    }

    private void save() {
        String text = binding.editText.getText().toString();
        if (text.isEmpty()) return;
        long createdAt = System.currentTimeMillis();
        Task task = new Task(text, createdAt);
        Bundle bundle = new Bundle();
        bundle.putSerializable("text", task);
        getParentFragmentManager().setFragmentResult("rk_task", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}