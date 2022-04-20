package br.senai.sp.todolistapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.senai.sp.todolistapp.R;
import br.senai.sp.todolistapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //instancia o binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //seta nna content view a raiz (root) do binding
        setContentView(binding.getRoot());
    }
}