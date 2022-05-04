package br.senai.sp.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;
import java.util.Calendar;

import br.senai.sp.todolistapp.R;
import br.senai.sp.todolistapp.database.AppDataBase;
import br.senai.sp.todolistapp.database.AppDataBase;
import br.senai.sp.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.todolistapp.model.Tarefa;

public class CadTarefaFragment extends Fragment {


    private FragmentCadTarefaBinding binding;
    //variável para datePicker
    private DatePickerDialog datePicker;
    int ano, mes, dia;
    //variável para obter a data atual
    Calendar dataAtual;
    //variável para data formatada
    String dataFormatada = "";
    //variavel para a database
    AppDataBase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancia a database
        database = AppDataBase.getDatabase(getContext());

        //instanciar o binding
        binding = FragmentCadTarefaBinding  .inflate(getLayoutInflater(),container, false);

        //instanciar a data atual
        dataAtual = Calendar.getInstance();
        //obter ano, mes e dia, na data atual
        ano = dataAtual.get(Calendar.YEAR);
        mes = dataAtual.get(Calendar.MONTH);
        dia = dataAtual.get(Calendar.DAY_OF_MONTH);

        //instanciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, year, month, day) -> {
            //ao escolher uma data no datPicker cai aq
            //passar para as variaveis globais
            ano = year;
            dia = day;
            mes = month;
            //formata a data
            dataFormatada = String.format("%02d/%02d/%04d", day, month + 1, year);
            //aplica a data formatada no botao
            binding.btData.setText(dataFormatada);
        }, ano, mes, dia);
        //ação do click do  otao de seleção da data
        binding.btData.setOnClickListener(v -> {
            datePicker.show();
        });
        //listener do botao salvar
        binding.btSalvar.setOnClickListener(v -> {
            if (binding.etTarefa.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Informe um título para a tarefa", Toast.LENGTH_SHORT).show();
            } else if (dataFormatada.isEmpty()) {
                Toast.makeText(getContext(), "Informe uma data para a tarefa", Toast.LENGTH_SHORT).show();
            } else {
                //criar uma tarefa
                Tarefa tarefa = new Tarefa();
                tarefa.setTitulo(binding.etTarefa.getText().toString());
                tarefa.setDescricao(binding.etDesc.getText().toString());
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                //criar um calendar
                Calendar dataPrevista = Calendar.getInstance();
                dataPrevista.set(ano, mes, dia);
                //passa os milisegundos da data para a data prevista
                tarefa.setDataPrevista(dataPrevista.getTimeInMillis());
                //salvar a tarefa
                new InsertTarefa().execute(tarefa);
            }
        });
        //retorna a view raiz (root) do biding
        return binding.getRoot();
    }

    //Asynctask para inserir Tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            //pegar a tarefa a partir do vetor
            Tarefa t = tarefas[0];
            try {
                //chamar o metodo para salvar a tarefa
                database.getTarefaDao().insert(t);
                //retornar
                return "ok";
            }catch (Exception error){
                error.printStackTrace();
                return error.getMessage();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("ok")){
                Log.w("result","tudo certo por aq man");
                Toast.makeText(getContext(), "Tarefa inserida com sucesso", Toast.LENGTH_LONG).show();
                //voltar ao fragment anterior
                getActivity().onBackPressed();
            }else{
                Log.w("result",result);
                Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
            }
        }
    }
}