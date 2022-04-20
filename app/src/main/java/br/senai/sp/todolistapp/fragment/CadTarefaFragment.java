package br.senai.sp.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import br.senai.sp.todolistapp.R;
import br.senai.sp.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.todolistapp.databinding.FragmentPrincipalBinding;

public class CadTarefaFragment extends Fragment {

    private FragmentCadTarefaBinding binding;
    //variável para datePicker
    private DatePickerDialog datePicker;
    int ano, mes, dia;
    //variável para obter a data atual
    Calendar dataAtual;
    //variável para data formatada
    String dataFormatada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentCadTarefaBinding  .inflate(getLayoutInflater(),container, false);

        //instanciar a data atual
        dataAtual = Calendar.getInstance();
        //obter ano, mes e dia, na data atual
        ano = dataAtual.get(Calendar.YEAR);
        mes = dataAtual.get(Calendar.MONTH);
        dia = dataAtual.get(Calendar.DAY_OF_MONTH);

        //instancia o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia)->{
            //ao escolher uma data no datePicker, cai aqui

        },ano,mes,dia);

        //ação do click do botão de seleção da data
        binding.btData.setOnClickListener(v ->{
            datePicker.show();
        });

        //retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}