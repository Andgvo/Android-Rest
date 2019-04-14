package com.example.androidclient.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.androidclient.R;
import com.example.androidclient.dao.ComentarioDAO;
import com.example.androidclient.dao.PostDAO;
import com.example.androidclient.dto.Comentario;
import com.example.androidclient.dto.Post;
import com.example.androidclient.dto.Usuario;
import com.example.androidclient.utilerias.AdapterComentario;

import java.text.SimpleDateFormat;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    private static final SimpleDateFormat FORMATO = new SimpleDateFormat("yyyy-MM-dd");
    private final PostDAO dao = new PostDAO(this);
    private final ComentarioDAO daoComentario = new ComentarioDAO(this);
    private Post post;
    private Usuario usuario;
    private List<Comentario> listaComentarios;
    private Usuario usuarioAutor;

    private TextView tvTitulo;
    private TextView tvCategoria;
    private TextView tvFecha;
    private TextView tvResumen;
    private TextView tvContenido;
    private RecyclerView recyclerPost;

    private FloatingActionButton fabInsertComentario;
    private FloatingActionButton fabUpdatePost;
    private FloatingActionButton fabDeltePost;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tvTitulo = (TextView) findViewById(R.id.xtvPostTitulo);
        tvCategoria = (TextView) findViewById(R.id.xtvPostCategoria);
        tvFecha = (TextView) findViewById(R.id.xtvPostFecha);
        tvResumen = (TextView) findViewById(R.id.xtvPostResumen);
        tvContenido = (TextView) findViewById(R.id.xtvPostContenido);
        recyclerPost = (RecyclerView) findViewById( R.id.xrecyclerIdPost );

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        post = (Post) getIntent().getSerializableExtra("post");
        listaComentarios = daoComentario.readAll(post);

        recyclerPost.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        AdapterComentario adapterPost = new AdapterComentario(listaComentarios);
        adapterPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comentario comentarioAux = listaComentarios.get(recyclerPost.getChildAdapterPosition(v));
                if(usuario.getIdUsuario() == comentarioAux.getIdUsuario().getIdUsuario()) {
                    Intent itn = new Intent(v.getContext(), ComentarioFormActivity.class);
                    itn.putExtra("post", post);
                    itn.putExtra("usuario", usuario);
                    itn.putExtra("comentario", comentarioAux);
                    startActivity(itn);
                }
            }
        });
        recyclerPost.setAdapter(adapterPost);
        fabInsertComentario = (FloatingActionButton) findViewById(R.id.xfabInsertComentario);
        fabUpdatePost = (FloatingActionButton) findViewById(R.id.xfabUpdatePost);
        fabDeltePost = (FloatingActionButton) findViewById(R.id.xfabDeltePost);
        if(post != null){
            setTextContent(post);
            if(post.getIdUsuario().getIdUsuario() == usuario.getIdUsuario()){
                fabDeltePost.setVisibility(View.VISIBLE);
                fabUpdatePost.setVisibility(View.VISIBLE);
            }

        }
        fabInsertComentario.setOnClickListener( setListener(ComentarioFormActivity.class) );
        fabUpdatePost.setOnClickListener( setListener(PostFormActivity.class) );
        fabDeltePost.setOnClickListener( deleteComentario() );
    }

    private View.OnClickListener deleteComentario() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                dao.delete(post);
                finish();
            }
        };
    }

    private View.OnClickListener setListener(final Class<?> clase){
        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent itn = new Intent(PostActivity.this, clase);
                itn.putExtra("post",post);
                itn.putExtra("usuario",usuario);
                startActivity(itn);
            }
        };
    }

    private void setTextContent(Post post){
        tvTitulo.setText(post.getTituloPost());
        tvCategoria.setText(post.getCategoriaPost());
        tvFecha.setText(FORMATO.format(post.getFechaPost()) + " por "+post.getIdUsuario().getNombreUsuario());
        tvResumen.setText(post.getResumenPost());
        tvContenido.setText(post.getContenidoPost());
    }
}
