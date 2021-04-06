package com.fab.challengeaa001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.fab.challengeaa001.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void showToolbar(){
        binding.mainToolbar.toolbar.setVisibility(View.VISIBLE);
    }
    public void hideToolbar(){
        binding.mainToolbar.toolbar.setVisibility(View.GONE);
    }

    public void setToolbarTitle(String title){
        binding.mainToolbar.toolbar.setTitle(title);
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}