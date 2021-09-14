package com.geek.taskapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geek.taskapp.R;
import com.geek.taskapp.databinding.FragmentHomeBinding;
import com.geek.taskapp.models.Task;

public class HomeFragment extends Fragment implements TaskAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    private TaskAdapter adapter;
    private Task task;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
        binding.fab.setOnClickListener(v -> openTaskFragment());

        getParentFragmentManager().setFragmentResultListener("rk_task", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                task = (Task) result.getSerializable("text");
                adapter.addItem(task);
            }
        });

    }

    private void initList() {
        adapter.setListener(this);
        binding.rvHome.setAdapter(adapter);
    }

    private void openTaskFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.taskFragment);
    }

    @Override
    public void onLongClick(int position) {
        Task task = adapter.getItem(position);
        new AlertDialog.Builder(requireContext())
                .setTitle("Удаление")
                .setMessage("Удалить запись \"" + task + "\" ?")
                .setNegativeButton("Нет", null)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.removeItem(position);
                    }
                }).show();
    }

    @Override
    public void onClick(int position) {

    }
}