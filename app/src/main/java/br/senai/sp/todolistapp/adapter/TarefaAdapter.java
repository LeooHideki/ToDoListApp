package br.senai.sp.todolistapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.senai.sp.todolistapp.R;
import br.senai.sp.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>{

    //variável para a lista de tarefas
    private List<Tarefa> tarefas;
    //variável para o Context
    private Context context;
    //variável pro listener
    private OnTarefaClickListener listenerTarefa;

    //construtor que recebe os parâmetros para o Adapter
    public TarefaAdapter(List<Tarefa> lista, Context contexto, OnTarefaClickListener listenerTarefa){
        this.tarefas = lista;
        this.context = contexto;
        this.listenerTarefa = listenerTarefa;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflar a view do viewHolder
                View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefas, parent, false);
        //retorna uma viewHolder
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
    //obtem a tarefa atravéz da position
        Tarefa t = tarefas.get(position);
        //transportar a info da tarefa para o holder
        holder.tvTitulo.setText(t.getTitulo());
        //formata a data e exibe no textView
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));
        //verifica se está concluída
        if(t.isConcluida()){
            holder.tvStatus.setText(R.string.finalizada);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else{
            holder.tvStatus.setText(R.string.aberta);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        }
        //implementar o click na tarefa
        holder.itemView.setOnClickListener(v -> {
            listenerTarefa.onCLick(v, t);

        });
    }

    @Override
    public int getItemCount() {
        if(tarefas !=null){
            return tarefas.size();
        }
        return 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder {
        //Variáveis para os componentes do layout
        TextView tvTitulo, tvData, tvStatus;

        public TarefaViewHolder(View view){
            super(view);
            //passar da view para os componentes
            tvTitulo = view.findViewById(R.id.tvNomeTarefa);
            tvData = view.findViewById(R.id.tvNomeData);
            tvStatus = view.findViewById(R.id.tvStatus);
        }
    }
    //iinterface para o click na tarefa
    public interface OnTarefaClickListener {
        void onCLick(View v, Tarefa t);

    }
}
