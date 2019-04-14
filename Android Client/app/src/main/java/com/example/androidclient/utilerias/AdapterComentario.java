package com.example.androidclient.utilerias;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidclient.R;
import com.example.androidclient.dto.Comentario;

import java.util.List;

public class AdapterComentario extends RecyclerView.Adapter<AdapterComentario.ViewHolderComentario> implements View.OnClickListener{

    List<Comentario> listaComentario;
    View.OnClickListener listener;

    public AdapterComentario(List<Comentario> listaComentario) {
        this.listaComentario = listaComentario;
    }

    @Override
    public AdapterComentario.ViewHolderComentario onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, null, false);
        view.setOnClickListener(this);
        return new AdapterComentario.ViewHolderComentario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComentario.ViewHolderComentario viewHolderComentario, int i) {
        viewHolderComentario.tvTitulo.setText( listaComentario.get(i).getIdUsuario().getNombreUsuario() );
        viewHolderComentario.tvFecha.setText( listaComentario.get(i).getFechaComentario().toString() );
        viewHolderComentario.tvContenido.setText( listaComentario.get(i).getContenidoComentario() );
    }

    @Override
    public int getItemCount() {
        return listaComentario.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class ViewHolderComentario extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        TextView tvFecha;
        TextView tvContenido;

        public ViewHolderComentario(@NonNull View itemView) {
            super(itemView);
            tvTitulo = (TextView) itemView.findViewById(R.id.xtvItemAutor);
            tvFecha = (TextView) itemView.findViewById(R.id.xtvItemFecha);
            tvContenido = (TextView) itemView.findViewById(R.id.xtvItemContenido);
        }
    }
}
