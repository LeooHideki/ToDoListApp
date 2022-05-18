package br.senai.sp.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import br.senai.sp.todolistapp.R;
import br.senai.sp.todolistapp.databinding.FragmentDetalheTarefaBinding;
import br.senai.sp.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.todolistapp.model.Tarefa;

public class DetalheTarefaFragment extends Fragment {

    private FragmentDetalheTarefaBinding binding;
    //variável para tarefa
    Tarefa tarefa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //instanciar o binding
        binding = FragmentDetalheTarefaBinding.inflate(getLayoutInflater(),container, false);



        //verifica se foi passado algo no bundle
        if(getArguments() != null){
            //recupera a tarefa do bundle
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            //popular as informações da tarefa
            binding.titulo.setText(tarefa.getTitulo());
            binding.descricao.setText(tarefa.getDescricao());
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            binding.data.setText(formatador.format(tarefa.getDataPrevista()));
            //variável para pendurar a tarefa
            Bundle bundle= new Bundle();
            //pendura a tarefa no bundle
            bundle.putSerializable("tarefa",tarefa);
            binding.btNovaSubTarefa.setOnClickListener(v ->{

                NavHostFragment.findNavController(DetalheTarefaFragment.this).navigate(R.id.action_detalheTarefaFragment_to_cadSubtarefaFragment, bundle);
            });
        }

        //retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}