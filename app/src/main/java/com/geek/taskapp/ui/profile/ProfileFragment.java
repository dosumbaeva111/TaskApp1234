package com.geek.taskapp.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.geek.taskapp.Prefs;
import com.geek.taskapp.R;
import com.geek.taskapp.databinding.FragmentProfileBinding;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private Uri imageUri;
    public Prefs prefs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(requireContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            imageUri = data.getData();
            prefs.saveImage(String.valueOf(imageUri));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (prefs.getImage() != null) {
            imageUri = Uri.parse(prefs.getImage());
            Glide.with(requireContext()).load(imageUri).circleCrop().into(binding.containerForImage);
        } else
            Glide.with(requireContext()).load(R.drawable.ic_baseline_account_circle_24).circleCrop().into(binding.containerForImage);

        if (prefs.getUserName() != null) {
            binding.etUsername.setText(prefs.getUserName());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        prefs.saveUserData(binding.etUsername.getText().toString());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId() == R.id.clear_data) {
            prefs.deleteImg();
            prefs.deleteUserName();
            binding.etUsername.setText("");
            Glide.with(requireContext()).load(R.drawable.ic_baseline_account_circle_24).circleCrop().into(binding.containerForImage);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}