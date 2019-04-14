package com.example.androidclient.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidclient.MainActivity;
import com.example.androidclient.R;
import com.example.androidclient.dao.ComentarioDAO;
import com.example.androidclient.dao.PostDAO;
import com.example.androidclient.dto.Comentario;
import com.example.androidclient.dto.Post;
import com.example.androidclient.dto.Usuario;
import com.example.androidclient.utilerias.AdapterComentario;
import com.example.androidclient.utilerias.AdapterPost;

import java.util.List;

public class PostActivity extends AppCompatActivity {
    private static final String[] ITEMS_CATEGORIA = new String[]{"Java", "Android"};
    private final PostDAO dao = new PostDAO(this);
    private final ComentarioDAO daoComentario = new ComentarioDAO(this);
    private long idPostInsert;
    private Post post;
    private Usuario usuario;
    private List<Comentario> listaComentarios;
    private Usuario usuarioAutor;

    private LinearLayout llManageSinglePost;
    private LinearLayout llInsertSinglePost;

    private TextView tvTitulo;
    private TextView tvCategoria;
    private TextView tvFecha;
    private TextView tvResumen;
    private TextView tvContenido;
    private RecyclerView recyclerPost;

    private FloatingActionButton fabInsertComentario;
    private FloatingActionButton fabUpdatePost;
    private FloatingActionButton fabDeltePost;

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
        recyclerPost.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        listaComentarios = daoComentario.readAll();
        AdapterComentario adapterPost = new AdapterComentario(listaComentarios);
        adapterPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent itn = new Intent(v.getContext(), PostActivity.class);
                //Post postAux = listaPost.get(recyclerPost.getChildAdapterPosition(v));
                //itn.putExtra("post",postAux);
                //itn.putExtra("usuario",usuarioInternFragment);
                //itn.putExtra("usuario",usuario);
                //Toast.makeText(rootView.getContext(),"Post = "+postAux, Toast.LENGTH_SHORT).show();
                //startActivity(itn);
            }
        });
        recyclerPost.setAdapter(adapterPost);
        Toast.makeText(getApplicationContext(),"lista_= " +listaComentarios, Toast.LENGTH_SHORT).show();
        fabInsertComentario = (FloatingActionButton) findViewById(R.id.xfabInsertComentario);
        fabUpdatePost = (FloatingActionButton) findViewById(R.id.xfabUpdatePost);
        fabDeltePost = (FloatingActionButton) findViewById(R.id.xfabDeltePost);

        //llInsertSinglePost = (LinearLayout) findViewById(R.id.xllPostInsert);
        //llManageSinglePost = (LinearLayout) findViewById(R.id.xllPostUpdate);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        post = (Post) getIntent().getSerializableExtra("post");

        if(post != null){
            setTextContent(post);
            //llInsertSinglePost.setVisibility(View.GONE);
            //llManageSinglePost.setVisibility(View.VISIBLE);
        }
        //Preparing Recycler view


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
        tvFecha.setText(post.getFechaPost().toString());
        tvResumen.setText(post.getResumenPost());
        tvContenido.setText(post.getContenidoPost());
    }
}
