package com.example.androidclient.utilerias;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidclient.R;
import com.example.androidclient.dto.Post;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolderPost> implements View.OnClickListener{
    private static final SimpleDateFormat FORMATO = new SimpleDateFormat("yyyy-MM-dd");
    List<Post> listaPost;
    View.OnClickListener listener;

    public AdapterPost(List<Post> listaPost) {
        this.listaPost = listaPost;
    }

    @Override
    public ViewHolderPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, null, false);
        view.setOnClickListener(this);
        return new ViewHolderPost(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPost viewHolderPost, int i) {
        viewHolderPost.tvTitulo.setText( listaPost.get(i).getTituloPost() );
        viewHolderPost.tvFecha.setText( FORMATO.format(listaPost.get(i).getFechaPost()) );
        viewHolderPost.tvResumen.setText( listaPost.get(i).getResumenPost() );
        viewHolderPost.tvContenido.setText( listaPost.get(i).getContenidoPost() );
    }

    @Override
    public int getItemCount() {
        return listaPost.size();
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

    public class ViewHolderPost extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        TextView tvFecha;
        TextView tvResumen;
        TextView tvContenido;

        public ViewHolderPost(@NonNull View itemView) {
            super(itemView);
            tvTitulo = (TextView) itemView.findViewById(R.id.xtvTitulo);
            tvFecha = (TextView) itemView.findViewById(R.id.xtvFecha);
            tvResumen = (TextView) itemView.findViewById(R.id.xtvResumen);
            tvContenido = (TextView) itemView.findViewById(R.id.xtvContenido);
        }
    }
}