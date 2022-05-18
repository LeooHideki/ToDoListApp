package br.senai.sp.todolistapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.todolistapp.R;
import br.senai.sp.todolistapp.adapter.TarefaAdapter;
import br.senai.sp.todolistapp.database.AppDataBase;
import br.senai.sp.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.todolistapp.model.Tarefa;

public class PrincipalFragment extends Fragment {
    private FragmentPrincipalBinding binding;
    //variável para a lista
    private List<Tarefa> tarefas;
    //variável para o adapter
    private TarefaAdapter adapter;
    //variável para a database
    private AppDataBase dataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //instanciar o binding
        binding = FragmentPrincipalBinding.inflate(getLayoutInflater(),container, false);

        binding.btNovaTarefa.setOnClickListener(v ->{
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefaFragment);
        });

        //instancia a database
        dataBase = AppDataBase.getDatabase(getContext());

        //define o layout manager do recycler
        binding.recyclerTarefas.setLayoutManager(new LinearLayoutManager(getContext()));

        //executar a asynctask
        new ReadTarefa().execute();

        //retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>>{
        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            //buscar as tarefas e guardar na variável tarefas
            tarefas = dataBase.getTarefaDao().listar();
            return tarefas;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            //instancia o adapter
            adapter = new TarefaAdapter(tarefas, getContext(), listenerClick);
            //aplica o adapter no recycler
            binding.recyclerTarefas.setAdapter(adapter);

        }
    }
    //listener para click nas tarefas
    private TarefaAdapter.OnTarefaClickListener listenerClick = (view, tarefa) -> {
        //variável para pendurar a tarefa
        Bundle bundle= new Bundle();
        //pendura a tarefa no bundle
        bundle.putSerializable("tarefa",tarefa);
        //navega para o fragment de detalhes
        NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_detalheTarefaFragment, bundle);
    };
}