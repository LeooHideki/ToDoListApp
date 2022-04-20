package br.senai.sp.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.todolistapp.R;
import br.senai.sp.todolistapp.databinding.FragmentCadSubtarefaBinding;
import br.senai.sp.todolistapp.databinding.FragmentPrincipalBinding;

public class CadSubtarefaFragment extends Fragment {

    private FragmentCadSubtarefaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentCadSubtarefaBinding.inflate(getLayoutInflater(),container, false);

        //retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}